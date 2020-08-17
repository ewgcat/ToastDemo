package com.lishuaihua.toast.style

interface IToastStyle {
    /**
     * 吐司的重心
     */
    var gravity: Int

    /**
     * X轴偏移
     */
    var xOffset: Int

    /**
     * Y轴偏移
     */
    var yOffset: Int

    /**
     * 吐司 Z 轴坐标
     */
    var z: Int

    /**
     * 圆角大小
     */
    var cornerRadius: Int

    /**
     * 背景颜色
     */
    var backgroundColor: Int

    /**
     * 文本颜色
     */
    var textColor: Int

    /**
     * 文本大小
     */
    var textSize: Float

    /**
     * 最大行数
     */
    var maxLines: Int

    /**
     * 开始内边距
     */
    var paddingStart: Int


    /**
     * 顶部内边距
     */
    var paddingTop: Int


    /**
     * 结束内边距
     */
    var paddingEnd: Int

    /**
     * 底部内边距
     */
    var paddingBottom: Int
}