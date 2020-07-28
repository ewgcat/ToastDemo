package com.lishuaihua.toast.style

import android.content.Context

open class ToastBlackStyle(context: Context?) : BaseToastStyle(context!!) {
    override var cornerRadius: Int = dp2px(8f)
    override var backgroundColor: Int = -0x78000000
    override var textColor: Int = -0x11000001
    override var textSize: Float = sp2px(14f).toFloat()
    override var paddingStart: Int = dp2px(24f)
    override var paddingTop: Int = dp2px(16f)
    override var paddingEnd: Int = dp2px(24f)
    override var paddingBottom: Int= dp2px(16f)
}