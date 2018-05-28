package com.example.administrator.myandroid.viewss;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by whq on 2018/5/28 0028.
 * <p>
 * 继承于View的控件需要对warp属性进行处理即重写onMeasure方法
 * 自定义ViewGroup需要对warp_contnet属性进行处理
 */

public class HorizontalView extends ViewGroup {

    int interceptX, interceptY, lastX, lastY;

    public HorizontalView(Context context) {
        super(context);
    }

    public HorizontalView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public HorizontalView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);

        //测量子元素的尺寸
        measureChildren(widthMeasureSpec, heightMeasureSpec);
        //如果没有子元素就设置宽高都为0
        if (getChildCount() == 0) {
            setMeasuredDimension(0, 0);
        }
        //宽高都为AT_MOST,则宽度设置为所有子元素的和，高度为第一个子元素的高度
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            View childOne = getChildAt(0);
            int childWidth = childOne.getMeasuredWidth();
            int childHeight = childOne.getMeasuredHeight();
            setMeasuredDimension(childWidth * getChildCount(), childHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            //宽度是AT_MOST
            setMeasuredDimension(getChildAt(0).getMeasuredWidth() * getChildCount(), heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            //如果高度是AT_MOST，自适应
            setMeasuredDimension(widthSpecSize, getChildAt(0).getMeasuredHeight());
        }
    }

    /**
     * 放置元素
     */
    @Override
    protected void onLayout(boolean b, int l, int top, int right, int bottom) {
        //1.先清楚元素个数
        int childCount = getChildCount();
        int left = 0;
        View child;

        //2.for循环，查出元素宽度并放置
        for (int i = 0; i < childCount; i++) {
            //先获取一个元素
            child = getChildAt(i);
            //如果这个元素是显示的
            if (child.getVisibility() != GONE) {
                //计算这个元素的宽度
                int width = child.getMeasuredWidth();
                //给这个元素安放位置
                layout(left, 0, left + width, child.getMeasuredHeight());
                //距离左边元素距离
                left += width;
                //这是没有考虑HorizontalView的padding和子元素Margin的情况
            }
        }
    }

    /**
     * 解决滑动冲突，如果该HorizontalView的子元素中有ListView就会有滑动冲突，这个时候
     * 监听滑动事件如果是水平滑动，就让父控件进行拦截
     */

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        boolean intercept = false;
        int x = (int) ev.getX();
        int y = (int) ev.getY();
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                break;
            case MotionEvent.ACTION_MOVE:
                int offX = x - interceptX;
                int offY = y - interceptY;
                if(Math.abs(offX) - Math.abs(offY) > 0){
                    //如果是横向滑动就拦截
                    intercept = true;
                }
                break;
            case MotionEvent.ACTION_UP:

                break;
        }
        interceptX = x;
        interceptY = y;

        return intercept;
    }

    /**
     *父控件拦截后走OntouchEvent方法
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return super.onTouchEvent(event);
    }
}
