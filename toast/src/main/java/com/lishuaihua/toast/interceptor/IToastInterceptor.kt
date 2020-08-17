package com.lishuaihua.toast.interceptor

import android.widget.Toast

interface IToastInterceptor {
    /**
     * 根据显示的文本决定是否拦截该 Toast
     */
    fun intercept(toast: Toast?, text: CharSequence?): Boolean
}