package com.xiao.dragwillview;

/**
 * author:xiaokexin
 * function: 关闭监听器
 */
public interface OnDragCloseListener {

    void onDragBegin();

    void onDragging(float percent);

    void onDragEnd(boolean isShareElementMode);

    void onDragCancel();

    boolean intercept();

}
