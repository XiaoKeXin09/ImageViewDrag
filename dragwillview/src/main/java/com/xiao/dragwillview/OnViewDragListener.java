package com.xiao.dragwillview;


/**
 * author: xiaokexin
 * function：拖拽监听器
 */
public interface OnViewDragListener {

    /**
     * @param dx x方向坐标的变化
     * @param dy Y方向坐标的变化
     */
    void onDrag(float dx, float dy);
}
