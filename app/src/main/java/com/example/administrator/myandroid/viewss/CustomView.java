package com.example.administrator.myandroid.viewss;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;

/**
 * Created by whq on 2018/5/25.
 * 自定义view跟随手指移动
 */

public class CustomView extends View {
    int lastX;
    int lastY;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //获取手指触摸点的横坐标与纵坐标
        int x = (int) event.getX();
        int y = (int) event.getY();
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                lastX = x;
                lastY = y;
                break;
            case MotionEvent.ACTION_MOVE:
                //计算移动的距离
                int offX = x - lastX;
                int offY = y - lastY;
                //第一种调用layout重新放置View位置
          //      layout(getLeft()+offX, getTop()+offY, getRight()+offX, getBottom()+offY);
                //第二种offsetLeftAndRight()
                //对left和right进行偏移
            //    offsetLeftAndRight(offX);
                //对top和bottom进行偏移
            //    offsetTopAndBottom(offY);
                //第三种，LayoutParams改变布局参数
                /*LinearLayout.LayoutParams layoutParams = (LinearLayout.LayoutParams) getLayoutParams();
                layoutParams.leftMargin = getLeft() + offX;
                layoutParams.topMargin = getTop() + offY;
                setLayoutParams(layoutParams);*/
                //第四种，动画
                //在Activyt中custom.setAnimation(AnimationUtils.loadAnimation(this, R.anim.translate));
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
