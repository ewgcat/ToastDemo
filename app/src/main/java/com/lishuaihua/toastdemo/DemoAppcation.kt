package com.lishuaihua.toastdemo

import android.app.Application
import com.lishuaihua.toast.ToastUtils

class DemoAppcation : Application() {
    override fun onCreate() {
        super.onCreate()
        // 在 Application 中初始化
        ToastUtils.init(this)
    }
}