package com.lishuaihua.toast.style

import android.content.Context

class ToastQQStyle(context: Context?) : BaseToastStyle(context!!) {
    override var z: Int = 0
    override var cornerRadius: Int = dp2px(4f)
    override var backgroundColor: Int = -0xcccccd
    override var textColor: Int = -0x1c1c1d
    override var textSize: Float = sp2px(12f).toFloat()
    override var paddingStart: Int = dp2px(16f)
    override var paddingTop: Int = dp2px(14f)
    override var paddingEnd: Int = dp2px(16f)
    override var paddingBottom: Int = dp2px(14f)
}