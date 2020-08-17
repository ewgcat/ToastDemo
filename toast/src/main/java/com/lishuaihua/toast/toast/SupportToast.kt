package com.lishuaihua.toast.toast

import android.app.Application
import android.view.View
import com.lishuaihua.toast.helper.ToastHelper
import com.lishuaihua.toast.toast.BaseToast

class SupportToast(application: Application?) : BaseToast(application) {
    /** 吐司弹窗显示辅助类  */
    private val mToastHelper: ToastHelper

    /** Toast 的视图  */
    private var mView: View? = null

    /** Toast 的重心  */
    private var mGravity = 0

    /** 水平偏移  */
    private var mXOffset = 0

    /** 垂直偏移  */
    private var mYOffset = 0

    /** 水平间距百分比  */
    private var mHorizontalMargin = 0f

    /** 垂直间距百分比  */
    private var mVerticalMargin = 0f
    override fun show() {
        // 显示吐司
        mToastHelper.show()
    }

    override fun cancel() {
        // 取消显示
        mToastHelper.cancel()
    }

    override fun setView(view: View) {
        mView = view
        setMessageView(findMessageView(view))
    }

    override fun getView(): View {
        return mView!!
    }

    override fun setGravity(gravity: Int, xOffset: Int, yOffset: Int) {
        mGravity = gravity
        mXOffset = xOffset
        mYOffset = yOffset
    }

    override fun getGravity(): Int {
        return mGravity
    }

    override fun getXOffset(): Int {
        return mXOffset
    }

    override fun getYOffset(): Int {
        return mYOffset
    }

    override fun setMargin(
        horizontalMargin: Float,
        verticalMargin: Float
    ) {
        mHorizontalMargin = horizontalMargin
        mVerticalMargin = verticalMargin
    }

    override fun getHorizontalMargin(): Float {
        return mHorizontalMargin
    }

    override fun getVerticalMargin(): Float {
        return mVerticalMargin
    }

    init {
        mToastHelper =
            ToastHelper(this, application!!)
    }
}