package com.xiao.dragwillview;

import android.view.MotionEvent;


/**
 * author: xiaokexin
 */
public interface OnSingleFlingListener {

    /**
     *
     * @param e1        用户第一次触摸的动作事件
     * @param e2        用户最后一次触摸的动作事件
     * @param velocityX 用户水平投掷距离
     * @param velocityY 用户竖直投掷距离
     */
    boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY);
}
