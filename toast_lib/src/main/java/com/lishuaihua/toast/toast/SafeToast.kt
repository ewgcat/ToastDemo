package com.lishuaihua.toast.toast

import android.annotation.TargetApi
import android.app.Application
import android.os.Build
import android.os.Handler
import android.widget.Toast
import com.lishuaihua.toast.handler.SafeHandler

@TargetApi(Build.VERSION_CODES.KITKAT)
class SafeToast(application: Application?) : BaseToast(application) {
    init {

        // 反射 Toast 中的字段
        try {

            // 获取 mTN 字段对象
            val mTNField = Toast::class.java.getDeclaredField("mTN")
            mTNField.isAccessible = true
            val mTN = mTNField[this]

            // 获取 mTN 中的 mHandler 字段对象
            val mHandlerField =
                mTNField.type.getDeclaredField("mHandler")
            mHandlerField.isAccessible = true
            val mHandler = mHandlerField[mTN] as Handler

            // 偷梁换柱
            mHandlerField[mTN] =
                SafeHandler(mHandler)
        } catch (ignored: IllegalAccessException) {
        } catch (ignored: NoSuchFieldException) {
        }
    }
}