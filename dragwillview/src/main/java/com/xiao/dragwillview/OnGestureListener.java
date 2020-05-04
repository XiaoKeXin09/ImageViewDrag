package com.xiao.dragwillview;

/**
 * author：xiaokexin
 * function:手势监听器
 */
interface OnGestureListener {

    void onDrag(float dx, float dy);

    void onFling(float startX, float startY, float velocityX,
                 float velocityY);

    void onScale(float scaleFactor, float focusX, float focusY);

    void onRotate(int degrees, int pivotX, int pivotY);

    void onToRightAngle(int pivotX, int pivotY);

}