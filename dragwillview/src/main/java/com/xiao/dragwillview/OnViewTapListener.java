package com.xiao.dragwillview;

import android.view.View;

/**
 * author ：xiaokexin
 */
public interface OnViewTapListener {

    /**
     * @param view - View
     * @param x    - 用户从视图左侧点击的位置
     * @param y    - 用户从视图上方点击的位置.
     */
    void onViewTap(View view, float x, float y);
}
