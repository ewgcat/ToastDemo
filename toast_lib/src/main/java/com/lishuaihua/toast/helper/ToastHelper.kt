package com.lishuaihua.toast.helper

import android.R
import android.app.Application
import android.content.Context
import android.graphics.PixelFormat
import android.os.Handler
import android.os.Looper
import android.os.Message
import android.view.WindowManager
import android.view.WindowManager.BadTokenException
import android.widget.Toast
import com.lishuaihua.toast.strategy.IToastStrategy

internal class ToastHelper(
    /** 当前的吐司对象  */
    private val mToast: Toast, application: Application
) :
    Handler(Looper.getMainLooper()) {

    /** WindowManager 辅助类  */
    private val mWindowHelper: WindowHelper

    /** 当前应用的包名  */
    private val mPackageName: String

    /** 当前是否已经显示  */
    var show = false
    override fun handleMessage(msg: Message) {
        // 收到取消显示的消息
        cancel()
    }

    /***
     * 显示吐司弹窗
     */
    fun show() {
        if (!show) {
            /*
             这里解释一下，为什么不复用 WindowManager.LayoutParams 这个对象
             因为如果复用了，不同 Activity 之间不能共用一个，第一个 Activity 调用显示方法可以显示出来，但是会导致后面的 Activity 都显示不出来
             又或者说，非第一次调用显示方法的 Activity 都会把这个显示请求推送给之前第一个调用显示的 Activity 上面，如果第一个 Activity 已经销毁，还会报以下异常
             android.view.WindowManager$BadTokenException:
             Unable to add window -- token android.os.BinderProxy@ef1ccb6 is not valid; is your activity running?
             */
            val params = WindowManager.LayoutParams()

            /*
            // 为什么不能加 TYPE_TOAST，因为通知权限在关闭后设置显示的类型为 Toast 会报错
            // android.view.WindowManager$BadTokenException: Unable to add window -- token null is not valid; is your activity running?
            params.type = WindowManager.LayoutParams.TYPE_TOAST;
            */

            /*
            // 这个是旧版本的写法，新版本已经废弃，因为 Activity onPause 方法被调用后这里把 Toast 取消显示了，这样做的原因：防止内存泄露
            // 判断是否为 Android 6.0 及以上系统并且有悬浮窗权限
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && Settings.canDrawOverlays(mToast.getView().getContext())) {
                // 解决使用 WindowManager 创建的 Toast 只能显示在当前 Activity 的问题
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    params.type = WindowManager.LayoutParams.TYPE_APPLICATION_OVERLAY;
                } else {
                    params.type = WindowManager.LayoutParams.TYPE_PHONE;
                }
            }
            */params.height = WindowManager.LayoutParams.WRAP_CONTENT
            params.width = WindowManager.LayoutParams.WRAP_CONTENT
            params.format = PixelFormat.TRANSLUCENT
            params.windowAnimations = R.style.Animation_Toast
            params.flags = (WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
                    or WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                    or WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
            params.packageName = mPackageName
            // 重新初始化位置
            params.gravity = mToast.gravity
            params.x = mToast.xOffset
            params.y = mToast.yOffset
            params.verticalMargin = mToast.verticalMargin
            params.horizontalMargin = mToast.horizontalMargin
            try {
                val topActivity = mWindowHelper.topActivity
                if (topActivity != null && !topActivity.isFinishing) {
                    val windowManager =
                        topActivity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                    windowManager?.addView(mToast.view, params)
                }
                // 添加一个移除吐司的任务
                sendEmptyMessageDelayed(
                    hashCode(),
                    if (mToast.duration == Toast.LENGTH_LONG) IToastStrategy.LONG_DURATION_TIMEOUT.toLong() else IToastStrategy.SHORT_DURATION_TIMEOUT.toLong()
                )
                // 当前已经显示
                show = true
            } catch (ignored: IllegalStateException) {
                // 如果这个 View 对象被重复添加到 WindowManager 则会抛出异常
                // java.lang.IllegalStateException: View android.widget.TextView has already been added to the window manager.
                // 如果 WindowManager 绑定的 Activity 已经销毁，则会抛出异常
                // android.view.WindowManager$BadTokenException: Unable to add window -- token android.os.BinderProxy@ef1ccb6 is not valid; is your activity running?
            } catch (ignored: BadTokenException) {
            }
        }
    }

    /**
     * 取消吐司弹窗
     */
    fun cancel() {
        // 移除之前移除吐司的任务
        removeMessages(hashCode())
        if (show) {
            try {
                val topActivity = mWindowHelper.topActivity
                if (topActivity != null) {
                    val windowManager =
                        topActivity.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                    windowManager?.removeViewImmediate(mToast.view)
                }
            } catch (ignored: IllegalArgumentException) {
                // 如果当前 WindowManager 没有附加这个 View 则会抛出异常
                // java.lang.IllegalArgumentException: View=android.widget.TextView not attached to window manager
            }
            // 当前没有显示
            show = false
        }
    }

    init {
        mPackageName = application.packageName
        mWindowHelper = WindowHelper.register(
            this,
            application
        )
    }
}