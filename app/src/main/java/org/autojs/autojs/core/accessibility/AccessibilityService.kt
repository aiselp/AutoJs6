package org.autojs.autojs.core.accessibility

import android.accessibilityservice.AccessibilityServiceInfo
import android.os.Build
import android.util.Log
import android.view.KeyEvent
import android.view.accessibility.AccessibilityEvent
import android.view.accessibility.AccessibilityEvent.TYPE_VIEW_FOCUSED
import android.view.accessibility.AccessibilityEvent.TYPE_WINDOW_STATE_CHANGED
import android.view.accessibility.AccessibilityNodeInfo
import org.autojs.autojs.core.accessibility.SimpleActionAutomator.Companion.AccessibilityEventCallback
import org.autojs.autojs.core.automator.AccessibilityEventWrapper
import org.autojs.autojs.event.EventDispatcher
import org.autojs.autojs.pref.Pref
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import java.util.concurrent.locks.ReentrantLock

interface AccessibilityServiceCallback {
    fun onConnected()
    fun onDisconnected()
}
/**
 * Created by Stardust on 2017/5/2.
 */
open class AccessibilityService : android.accessibilityservice.AccessibilityService() {
    val onKeyObserver = OnKeyListener.Observer()
    val keyInterrupterObserver = KeyInterceptor.Observer()
    var fastRootInActiveWindow: AccessibilityNodeInfo? = null
    var bridge: AccessibilityBridge? = null
    private val eventBox = TreeMap<Int, AccessibilityEventCallback>()

    private val gestureEventDispatcher = EventDispatcher<GestureListener>()

    private val eventExecutor by lazy { Executors.newSingleThreadExecutor() }

    private fun eventNameToType(str: String): Int {
        return try {
            val sb = StringBuilder()
            sb.append("TYPE_")
            val upperCase = str.uppercase(Locale.getDefault())
            sb.append(upperCase)
            AccessibilityEvent::class.java.getField(sb.toString()).get(null) as Int
        } catch (unused: NoSuchFieldException) {
            throw IllegalArgumentException("unknown event type: $str")
        }
    }

    fun addAccessibilityEventCallback(name: String, callback: AccessibilityEventCallback) {
        eventBox[eventNameToType(name)] = callback
    }

    fun removeAccessibilityEventCallback(name: String) {
        eventBox.remove(eventNameToType(name))
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent) {
        if (instance != this) instance = this
        val type = event.eventType
        eventBox[type]?.onAccessibilityEvent(AccessibilityEventWrapper(event))
        if (containsAllEventTypes || eventTypes.contains(type)) {
            if (type == TYPE_WINDOW_STATE_CHANGED || type == TYPE_VIEW_FOCUSED) {
                rootInActiveWindow?.also { fastRootInActiveWindow = it }
            }
            for ((_, delegate) in delegates) {
                val types = delegate.eventTypes
                if (types == null || types.contains(type)) {
                    // long start = System.currentTimeMillis();
                    if (delegate.onAccessibilityEvent(this@AccessibilityService, event)) {
                        break
                    }
                    // Log.v(TAG, "millis: " + (System.currentTimeMillis() - start) + " delegate: " + entry.getValue().getClass().getName());
                }
            }
        }
    }

    override fun onInterrupt() {}

    override fun onKeyEvent(event: KeyEvent): Boolean {
        eventExecutor.execute {
            stickOnKeyObserver.onKeyEvent(event.keyCode, event)
            onKeyObserver.onKeyEvent(event.keyCode, event)
        }
        return keyInterrupterObserver.onInterceptKeyEvent(event)
    }

    @Suppress("DEPRECATION")
    @Deprecated("Deprecated in Java")
    override fun onGesture(gestureId: Int): Boolean {
        eventExecutor.execute {
            gestureEventDispatcher.dispatchEvent {
                onGesture(gestureId)
            }
        }
        return false
    }

    override fun getRootInActiveWindow(): AccessibilityNodeInfo? {
        return try {
            super.getRootInActiveWindow()
        } catch (e: Exception) {
            null
        }
    }

    override fun onDestroy() {
        Log.v(TAG, "onDestroy: $instance")
        instance = null
        bridge = null
        eventExecutor.shutdownNow()
        callback?.onDisconnected()
        super.onDestroy()
    }

    override fun onServiceConnected() {
        instance = this
        serviceInfo = serviceInfo.apply {
            AccessibilityServiceInfo.FLAG_INCLUDE_NOT_IMPORTANT_VIEWS.let {
                flags = (if (Pref.isStableModeEnabled) flags and it.inv() else flags or it)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                AccessibilityServiceInfo.FLAG_REQUEST_TOUCH_EXPLORATION_MODE.let {
                    flags = (if (Pref.isGestureObservingEnabled) flags or it else flags and it.inv())
                }
            }
        }
        callback?.onConnected()
        super.onServiceConnected()

        LOCK.lock()
        ENABLED.signalAll()
        LOCK.unlock()

        // FIXME: 2017/2/12 有时在无障碍中开启服务后这里不会调用服务也不会运行，安卓的BUG???
    }

    companion object {

        private const val TAG = "AccessibilityService"

        private val delegates = TreeMap<Int, AccessibilityDelegate>()
        private var containsAllEventTypes = false
        private val eventTypes = HashSet<Int>()

        private val LOCK = ReentrantLock()
        private val ENABLED = LOCK.newCondition()
        private var callback: AccessibilityServiceCallback? = null

        var instance: AccessibilityService? = null
            private set

        var bridge: AccessibilityBridge?
            get() = instance?.bridge
            set(value) {
                instance?.bridge = value
            }

        val stickOnKeyObserver = OnKeyListener.Observer()

        @JvmStatic
        fun isRunning() = instance != null

        @JvmStatic
        fun isNotRunning() = !isRunning()

        fun addDelegate(uniquePriority: Int, delegate: AccessibilityDelegate) {
            // 用于记录eventTypes中的事件id
            delegates[uniquePriority] = delegate
            val set = delegate.eventTypes
            if (set == null) {
                containsAllEventTypes = true
            } else {
                eventTypes.addAll(set)
            }
        }

        @JvmStatic
        fun disable() = try {
            true.also { instance?.disableSelf() }
        } catch (e: Exception) {
            false
        }

        fun waitForEnabled(timeout: Long): Boolean {
            if (isRunning()) {
                return true
            }
            LOCK.lock()
            try {
                if (isRunning()) {
                    return true
                }
                if (timeout == -1L) {
                    ENABLED.await()
                    return true
                }
                return ENABLED.await(timeout, TimeUnit.MILLISECONDS)
            } catch (e: InterruptedException) {
                e.printStackTrace()
                return false
            } finally {
                LOCK.unlock()
            }
        }
        @JvmStatic
        fun clearAccessibilityEventCallback() {
            instance?.eventBox?.clear()
        }
        fun setCallback(listener: AccessibilityServiceCallback) {
            callback = listener
        }

        interface GestureListener {
            fun onGesture(gestureId: Int)
        }
    }
}
