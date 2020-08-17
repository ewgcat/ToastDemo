package com.lishuaihua.toast.style

import android.content.Context
import android.util.TypedValue
import android.view.Gravity

abstract class BaseToastStyle(private val mContext: Context) :
    IToastStyle {
    override var gravity: Int = Gravity.CENTER

    override var xOffset: Int = 0

    override var yOffset: Int = 0

    override var z: Int = 30

    override var maxLines: Int = 5

    /**
     * dp转px
     */
    protected fun dp2px(dpValue: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dpValue,
            mContext.resources.displayMetrics
        ).toInt()
    }

    /**
     * sp转px
     */
    protected fun sp2px(spValue: Float): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_SP,
            spValue,
            mContext.resources.displayMetrics
        ).toInt()
    }

}