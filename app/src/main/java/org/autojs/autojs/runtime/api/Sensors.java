package org.autojs.autojs.runtime.api;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

import androidx.annotation.NonNull;

import org.autojs.autojs.core.eventloop.EventEmitter;
import org.autojs.autojs.core.looper.Loopers;
import org.autojs.autojs.runtime.ScriptBridges;
import org.autojs.autojs.runtime.ScriptRuntime;
import org.autojs.autojs.tool.MapBuilder;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by Stardust on 2018/2/5.
 */
public class Sensors extends EventEmitter {

    public class SensorEventEmitter extends EventEmitter implements SensorEventListener {

        public SensorEventEmitter(ScriptBridges bridges) {
            super(bridges);
        }

        @Override
        public void onSensorChanged(SensorEvent event) {
            Object[] args = new Object[event.values.length + 1];
            args[0] = event;
            for (int i = 1; i < args.length; i++) {
                args[i] = event.values[i - 1];
            }
            emit("change", args);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {
            emit("accuracy_change", accuracy);
        }

        public void unregister() {
            Sensors.this.unregister(this);
        }
    }

    public static class Delay {
        public static final int normal = SensorManager.SENSOR_DELAY_NORMAL;
        public static final int ui = SensorManager.SENSOR_DELAY_UI;
        public static final int game = SensorManager.SENSOR_DELAY_GAME;
        public static final int fastest = SensorManager.SENSOR_DELAY_FASTEST;
    }

    private static final Map<String, Integer> SENSORS = new MapBuilder<String, Integer>()
            .put("ACCELEROMETER", Sensor.TYPE_ACCELEROMETER)
            .put("AMBIENT_TEMPERATURE", Sensor.TYPE_AMBIENT_TEMPERATURE)
            .put("GRAVITY", Sensor.TYPE_GRAVITY)
            .put("GYROSCOPE", Sensor.TYPE_GYROSCOPE)
            .put("LIGHT", Sensor.TYPE_LIGHT)
            .put("LINEAR_ACCELERATION", Sensor.TYPE_LINEAR_ACCELERATION)
            .put("MAGNETIC_FIELD", Sensor.TYPE_MAGNETIC_FIELD)
            .put("ORIENTATION", Sensor.TYPE_ORIENTATION)
            .put("PRESSURE", Sensor.TYPE_PRESSURE)
            .put("PROXIMITY", Sensor.TYPE_PROXIMITY)
            .put("RELATIVE_HUMIDITY", Sensor.TYPE_RELATIVE_HUMIDITY)
            .put("TEMPERATURE", Sensor.TYPE_AMBIENT_TEMPERATURE)
            .build();

    public boolean ignoresUnsupportedSensor = false;

    @SuppressWarnings("InstantiationOfUtilityClass")
    public final Delay delay = new Delay();

    private final Set<SensorEventEmitter> mSensorEventEmitters = new HashSet<>();
    private final SensorManager mSensorManager;
    private final ScriptBridges mScriptBridges;
    private final SensorEventEmitter mNoOpSensorEventEmitter;
    private final ScriptRuntime mScriptRuntime;
    private final Loopers.AsyncTask mAsyncTask = new Loopers.AsyncTask("Sensors"){
        @Override
        public boolean onFinish(@NonNull Loopers loopers) {
            return !mSensorEventEmitters.isEmpty();
        }
    };


    public Sensors(Context context, ScriptRuntime runtime) {
        super(runtime.bridges);
        mSensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        mScriptBridges = runtime.bridges;
        mNoOpSensorEventEmitter = new SensorEventEmitter(runtime.bridges);
        mScriptRuntime = runtime;
        runtime.loopers.addAsyncTask(mAsyncTask);
    }

    public SensorEventEmitter register(String sensorName) {
        return register(sensorName, Delay.normal);
    }

    public SensorEventEmitter register(String sensorName, int delay) {
        if (sensorName == null) {
            throw new NullPointerException(Sensors.class.getSimpleName() + ".register");
        }
        Sensor sensor = getSensor(sensorName);
        if (sensor == null) {
            if (ignoresUnsupportedSensor) {
                emit("unsupported_sensor", sensorName);
                return mNoOpSensorEventEmitter;
            } else {
                return null;
            }
        }
        return register(sensor, delay);
    }

    private SensorEventEmitter register(@NonNull Sensor sensor, int delay) {
        SensorEventEmitter emitter = new SensorEventEmitter(mScriptBridges);
        mSensorManager.registerListener(emitter, sensor, delay);
        synchronized (mSensorEventEmitters) {
            mSensorEventEmitters.add(emitter);
        }
        return emitter;
    }

    public Sensor getSensor(String sensorName) {
        sensorName = sensorName.toUpperCase();
        Integer type = SENSORS.get(sensorName);
        type = type == null ? getSensorTypeByReflect(sensorName) : type;
        return type == null ? null : mSensorManager.getDefaultSensor(type);
    }

    private Integer getSensorTypeByReflect(String sensorName) {
        try {
            Field field = Sensor.class.getField("TYPE_" + sensorName);
            return (Integer) field.get(null);
        } catch (Exception e) {
            return null;
        }
    }

    public void unregister(SensorEventEmitter emitter) {
        if (emitter != null) {
            synchronized (mSensorEventEmitters) {
                mSensorEventEmitters.remove(emitter);
            }
            mSensorManager.unregisterListener(emitter);
        }
    }

    public void unregisterAll() {
        synchronized (mSensorEventEmitters) {
            for (SensorEventEmitter emitter : mSensorEventEmitters) {
                mSensorManager.unregisterListener(emitter);
            }
            mSensorEventEmitters.clear();
        }
        mScriptRuntime.loopers.removeAsyncTask(mAsyncTask);
    }
}
