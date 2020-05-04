package com.xiao.dragwillview;

import android.widget.ImageView;

/**
 * author: xiaokexin
 */
public interface OnPhotoTapListener {

    /**
     *
     *
     * @param view  view
     * @param x    可拉伸宽度
     * @param y    可拉伸高度
     */
    void onPhotoTap(ImageView view, float x, float y);
}
