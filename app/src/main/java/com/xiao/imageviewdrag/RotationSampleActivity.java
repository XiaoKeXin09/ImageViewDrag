package com.xiao.imageviewdrag;

import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.xiao.dragwillview.DragView;


public class RotationSampleActivity extends AppCompatActivity {

    private DragView mDragView;
    private final Handler handler = new Handler();
    private boolean rotating = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotation_sample);
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.inflateMenu(R.menu.rotation);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_rotate_10_right:
                        mDragView.setRotationBy(10);
                        return true;
                    case R.id.action_rotate_10_left:
                        mDragView.setRotationBy(-10);
                        return true;
                    case R.id.action_toggle_automatic_rotation:
                        toggleRotation();
                        return true;
                    case R.id.action_reset_to_0:
                        mDragView.setRotationTo(0);
                        return true;
                    case R.id.action_reset_to_90:
                        mDragView.setRotationTo(90);
                        return true;
                    case R.id.action_reset_to_180:
                        mDragView.setRotationTo(180);
                        return true;
                    case R.id.action_reset_to_270:
                        mDragView.setRotationTo(270);
                        return true;
                }
                return false;
            }
        });
        mDragView = findViewById(R.id.iv_photo);
        mDragView.setImageResource(R.drawable.wrapwill);
    }

    @Override
    protected void onPause() {
        super.onPause();
        handler.removeCallbacksAndMessages(null);
    }

    private void toggleRotation() {
        if (rotating) {
            handler.removeCallbacksAndMessages(null);
        } else {
            rotateLoop();
        }
        rotating = !rotating;
    }

    private void rotateLoop() {
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                mDragView.setRotationBy(1);
                rotateLoop();
            }
        }, 15);
    }
}
