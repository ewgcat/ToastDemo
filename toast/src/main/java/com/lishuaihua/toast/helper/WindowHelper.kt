package com.lishuaihua.toast.helper

import android.annotation.TargetApi
import android.app.Activity
import android.app.Application
import android.app.Application.ActivityLifecycleCallbacks
import android.os.Build
import android.os.Bundle

@TargetApi(Build.VERSION_CODES.KITKAT)
internal class WindowHelper private constructor(
    /** 用于 Activity 暂停时移除 WindowManager  */
    private val mToastHelper: ToastHelper
) : ActivityLifecycleCallbacks {
    /**
     * 获取栈顶的 Activity
     */
    /** 栈顶 Activity  */
    var topActivity: Activity? = null
        private set

    /**
     * [Application.ActivityLifecycleCallbacks]
     */
    override fun onActivityCreated(
        activity: Activity,
        savedInstanceState: Bundle
    ) {
        topActivity = activity
    }

    override fun onActivityStarted(activity: Activity) {
        topActivity = activity
    }

    override fun onActivityResumed(activity: Activity) {
        topActivity = activity
    }

    // A 跳转 B 页面的生命周期方法执行顺序：
    // onPause(A) ---> onCreate(B) ---> onStart(B) ---> onResume(B) ---> onStop(A) ---> onDestroyed(A)
    override fun onActivityPaused(activity: Activity) {
        // 取消这个吐司的显示
        if (mToastHelper.show) {
            // 不能放在 onStop 或者 onDestroyed 方法中，因为此时新的 Activity 已经创建完成，必须在这个新的 Activity 未创建之前关闭这个 WindowManager
            // 调用取消显示会直接导致新的 Activity 的 onCreate 调用显示吐司可能显示不出来的问题，又或者有时候会立马显示然后立马消失的那种效果
            mToastHelper.cancel()
        }
    }

    override fun onActivityStopped(activity: Activity) {}
    override fun onActivitySaveInstanceState(
        activity: Activity,
        outState: Bundle
    ) {
    }

    override fun onActivityDestroyed(activity: Activity) {
        if (topActivity === activity) {
            // 移除对这个 Activity 的引用
            topActivity = null
        }
    }

    companion object {
        fun register(toast: ToastHelper, application: Application): WindowHelper {
            val window = WindowHelper(toast)
            application.registerActivityLifecycleCallbacks(window)
            return window
        }
    }

}