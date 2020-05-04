package com.xiao.imageviewdrag;

import android.os.Bundle;
import android.view.MotionEvent;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.xiao.dragwillview.DragCloseHelper;
import com.xiao.dragwillview.DragView;
import com.xiao.dragwillview.OnDragCloseListener;


/**
 * author :xiaokexin
 */
public class ActivityTransitionToActivity extends AppCompatActivity {

    private DragCloseHelper mDragCloseHelper;
    private DragView mDragView;
    private ConstraintLayout mConstraintLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_to);
        mConstraintLayout = findViewById(R.id.iv_preview_cl);
        mDragView = findViewById(R.id.iv_preview_iv);

        mDragCloseHelper = new DragCloseHelper(this);
        mDragCloseHelper.setShareElementMode(true);
        mDragCloseHelper.setDragCloseViews(mConstraintLayout, mDragView);
        mDragCloseHelper.setOnDragCloseListener(new OnDragCloseListener() {
            @Override
            public void onDragBegin() {

            }

            @Override
            public void onDragging(float percent) {

            }

            @Override
            public void onDragEnd(boolean isShareElementMode) {
                if (isShareElementMode) {
                    onBackPressed();
                }
            }

            @Override
            public void onDragCancel() {

            }

            @Override
            public boolean intercept() {
                // 默认false
                return false;
            }
        });
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (mDragCloseHelper.handleEvent(ev)) {
            return true;
        } else {
            return super.dispatchTouchEvent(ev);
        }
    }
}
