package com.xiao.dragwillview;

import android.graphics.RectF;

/**
 * author : xiaokexin
 */
public interface OnMatrixChangedListener {

    /**
     *
     * @param rect 显示可绘制的新边界的矩形
     */
    void onMatrixChanged(RectF rect);
}
