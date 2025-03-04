package org.autojs.autojs.core.ui

import android.view.View
import android.view.ViewGroup
import org.autojs.autojs.core.ui.inflater.util.Ids

/**
 * Created by Stardust on 2017/5/14.
 * Transformed by 抠脚本人 on Jul 10, 2023.
 */
object JsViewHelper {
    @JvmStatic
    fun findViewByStringId(view: View, id: String?): View? {
        view.findViewById<View>(Ids.parse(id))?.let { return it }
        if (view is ViewGroup) {
            for (i in 0 until view.childCount) {
                findViewByStringId(view.getChildAt(i), id)?.let { return it }
            }
        }
        return null
    }
}
