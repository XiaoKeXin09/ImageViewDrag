package com.xiao.dragwillview;


/**
 * author: xiaokexin
 */
public interface OnScaleChangedListener {

    /**
     * @param scaleFactor 比例因子
     * @param focusX      焦点X
     * @param focusY      焦点Y位置
     */
    void onScaleChange(float scaleFactor, float focusX, float focusY);
}
