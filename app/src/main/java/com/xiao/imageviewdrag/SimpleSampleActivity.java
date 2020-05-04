package com.xiao.imageviewdrag;

import android.annotation.SuppressLint;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.xiao.dragwillview.DragView;
import com.xiao.dragwillview.OnMatrixChangedListener;
import com.xiao.dragwillview.OnPhotoTapListener;
import com.xiao.dragwillview.OnSingleFlingListener;

import java.util.Random;

public class SimpleSampleActivity extends AppCompatActivity {

    static final String PHOTO_TAP_TOAST_STRING = "Photo Tap! X: %.2f %% Y:%.2f %% ID: %d";
    static final String SCALE_TOAST_STRING = "Scaled to: %.2ff";
    static final String FLING_LOG_STRING = "Fling velocityX: %.2f, velocityY: %.2f";

    private DragView mDragView;
    private TextView mCurrMatrixTv;

    private Toast mCurrentToast;

    private Matrix mCurrentDisplayMatrix = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_sample);

        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("Simple Sample");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        toolbar.inflateMenu(R.menu.main_menu);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @SuppressLint("DefaultLocale")
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.menu_zoom_toggle:
                        mDragView.setZoomable(!mDragView.isZoomable());
                        item.setTitle(mDragView.isZoomable() ? R.string.menu_zoom_disable : R.string.menu_zoom_enable);
                        return true;

                    case R.id.menu_scale_fit_center:
                        mDragView.setScaleType(ImageView.ScaleType.CENTER);
                        return true;

                    case R.id.menu_scale_fit_start:
                        mDragView.setScaleType(ImageView.ScaleType.FIT_START);
                        return true;

                    case R.id.menu_scale_fit_end:
                        mDragView.setScaleType(ImageView.ScaleType.FIT_END);
                        return true;

                    case R.id.menu_scale_fit_xy:
                        mDragView.setScaleType(ImageView.ScaleType.FIT_XY);
                        return true;

                    case R.id.menu_scale_scale_center:
                        mDragView.setScaleType(ImageView.ScaleType.CENTER);
                        return true;

                    case R.id.menu_scale_scale_center_crop:
                        mDragView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                        return true;

                    case R.id.menu_scale_scale_center_inside:
                        mDragView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
                        return true;

                    case R.id.menu_scale_random_animate:
                    case R.id.menu_scale_random:
                        Random r = new Random();

                        float minScale = mDragView.getMinimumScale();
                        float maxScale = mDragView.getMaximumScale();
                        float randomScale = minScale + (r.nextFloat() * (maxScale - minScale));
                        mDragView.setScale(randomScale, item.getItemId() == R.id.menu_scale_random_animate);

                        showToast(String.format(SCALE_TOAST_STRING, randomScale));

                        return true;
                    case R.id.menu_matrix_restore:
                        if (mCurrentDisplayMatrix == null)
                            showToast("You need to capture display matrix first");
                        else
                            mDragView.setDisplayMatrix(mCurrentDisplayMatrix);
                        return true;
                    case R.id.menu_matrix_capture:
                        mCurrentDisplayMatrix = new Matrix();
                        mDragView.getDisplayMatrix(mCurrentDisplayMatrix);
                        return true;
                }
                return false;
            }
        });
        mDragView = findViewById(R.id.iv_photo);
        mCurrMatrixTv = findViewById(R.id.tv_current_matrix);

        Drawable bitmap = ContextCompat.getDrawable(this, R.drawable.wrapwill);
        mDragView.setImageDrawable(bitmap);

        // Lets attach some listeners, not required though!
        mDragView.setOnMatrixChangeListener(new MatrixChangeListener());
        mDragView.setOnPhotoTapListener(new PhotoTapListener());
        mDragView.setOnSingleFlingListener(new SingleFlingListener());
    }

    private class PhotoTapListener implements OnPhotoTapListener {

        @SuppressLint("DefaultLocale")
        @Override
        public void onPhotoTap(ImageView view, float x, float y) {
            float xPercentage = x * 100f;
            float yPercentage = y * 100f;

            showToast(String.format(PHOTO_TAP_TOAST_STRING, xPercentage, yPercentage, view == null ? 0 : view.getId()));
        }
    }

    private void showToast(CharSequence text) {
        if (mCurrentToast != null) {
            mCurrentToast.cancel();
        }

        mCurrentToast = Toast.makeText(SimpleSampleActivity.this, text, Toast.LENGTH_SHORT);
        mCurrentToast.show();
    }

    private class MatrixChangeListener implements OnMatrixChangedListener {

        @Override
        public void onMatrixChanged(RectF rect) {
            mCurrMatrixTv.setText(rect.toString());
        }
    }

    private class SingleFlingListener implements OnSingleFlingListener {

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
            Log.d("DragView", String.format(FLING_LOG_STRING, velocityX, velocityY));
            return true;
        }
    }
}
