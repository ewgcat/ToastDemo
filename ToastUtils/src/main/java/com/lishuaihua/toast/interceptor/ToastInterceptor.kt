package com.lishuaihua.toast.interceptor

import android.widget.Toast
import com.lishuaihua.toast.interceptor.IToastInterceptor

class ToastInterceptor : IToastInterceptor {
    override fun intercept(toast: Toast?, text: CharSequence?): Boolean {
        // 如果是空对象或者空文本就进行拦截
        return text == null || "" == text.toString()
    }
}