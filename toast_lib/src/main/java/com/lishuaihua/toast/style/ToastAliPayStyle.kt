package com.lishuaihua.toast.style

import android.content.Context
import android.view.Gravity

class ToastAliPayStyle(context: Context?) : BaseToastStyle(context!!) {
    override var gravity: Int = Gravity.CENTER_HORIZONTAL or Gravity.BOTTOM
    override var yOffset: Int = dp2px(100f)
    override var cornerRadius: Int = dp2px(5f)
    override var backgroundColor: Int = -0x11a8a8a9
    override var textColor: Int = -0x1
    override var textSize: Float = sp2px(16f).toFloat()
    override var paddingStart: Int = dp2px(16f)
    override var paddingTop: Int = dp2px(10f)
    override var paddingEnd: Int = dp2px(16f)
    override var paddingBottom: Int= dp2px(10f)


}