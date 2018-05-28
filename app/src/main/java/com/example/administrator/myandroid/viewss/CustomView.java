package com.example.administrator.myandroid.viewss;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Scroller;

/**
 * Created by whq on 2018/5/25.
 * 自定义view跟随手指移动
 */

public class CustomView extends View {
    int lastX;
    int lastY;
    Scroller mScorller;

    public CustomView(Context context) {
        super(context);
    }

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mScorller = new Scroller(context);
    }

    public CustomView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

    }


    /**
     * 第六种方法，重写computeScroll()方法
     * 系统在绘制View的时候在draw()方法中调用该方法，在该方法中我们调用父类的scrollTo()方法并通过Scroller
     * 来不断获取当前的滚动值，每滑动一小段距离，就调用invalidate()方法不断进行重绘，就又会调用computeScroll()，
     * 不断移动一个小的距离连贯起来就实现了平滑移动效果
     *
     */

    @Override
    public void computeScroll() {
        super.computeScroll();
        if(mScorller.computeScrollOffset()){
            ((View)getParent()).scrollTo(mScorller.getCurrX(), mScorller.getCurrY());
            invalidate();
        }
    }

    /**
     * 定义该方法中，调用Scroller的startScroll()方法，在2000ms内沿X轴平移delta像素,
     * 在Activity中设置距离，调用该方法
     */

    public void smoothScrollTo(int destX, int destY){
        int scrollX = getScrollX();
        int delta = destX - scrollX;
        mScorller.startScroll(scrollX, 0, delta, 0, 2000);
        invalidate();
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
                layout(getLeft()+offX, getTop()+offY, getRight()+offX, getBottom()+offY);
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
                //第五种，ScrollTo和ScrollBy，这个过程是瞬时完成的，体验不好，所以少用
                //第六种，Scroller实现有过渡效果的滑动，需要与View的computeScroll()方法配合
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        return true;
    }
}
