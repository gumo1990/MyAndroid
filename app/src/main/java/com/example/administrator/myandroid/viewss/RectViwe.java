package com.example.administrator.myandroid.viewss;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.utils.ComUtils;

/**
 * Created by whq on 2018/5/28.
 * 继承View不仅要重写onDraw方法，还要重写onMeasure方法
 * 自定义View需要考虑warp_content,padding属性设置，为了方便配置自己的自定义View，
 * 还会对外提供自定义的属性，如果要改变触控的逻辑，还要重写onTouchEvent等触控方法。
 *
 * Paint.UNDERLINE_TEXT_FLAG--下划线
 * Paint.STRIKE_THRU_TEXT_FLAG---中划线
 * Paint.STRIKE_THRU_TEXT_FLAG|Paint.ANTI_ALIAS_FLAG--设置中划线并加清晰
 *
 * 直接继承View的自定义View，他在xml中的wrap_contnet和match_parent属性是一样的，
 * 如果要实现自定义view的wrap_content需要重写onMeasure，并对自定义View的wrap_content属性进行处理
 *
 * UNSPECIFIED---未指定模式，View想多大就多大，父容器不做限制，一般用于系统内存的测量
 * AT_MOST    ---最大模式，对应于wrap_content，子View的最终大小是父View制定值，子View大小不能大于这个值
 * EXACTLY    ---精准模式，对应于match_parent属性和具体数值，父容器测量出View所需的大小
 *
 * 在values文件夹中定义attrs.xml，设置自定义属性
 */

public class RectViwe extends View {

  //  Paint mPain = new Paint(0);//取消设置的划线
    Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    int mColor = Color.YELLOW;

    public RectViwe(Context context) {
        super(context);
        initDraw();
    }

    public RectViwe(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mTypeArray = context.obtainStyledAttributes(attrs, R.styleable.RectView);
        //提取RectView属性集合的rect_color属性，如果没设置，默认值为Color.RED
        mColor = mTypeArray.getColor(R.styleable.RectView_rect_color, Color.RED);
        //获取资源后及时回收
        mTypeArray.recycle();
        
        initDraw();
    }

    public RectViwe(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    private void initDraw(){
        mPaint.setColor(mColor);
        mPaint.setStrokeWidth(2.0f);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //这里的宽高就是xml中相对于自适应的设置
        int padddingleft = getPaddingLeft();
        int padright = getPaddingRight();
        int padtop = getPaddingTop();
        int padbottom = getPaddingBottom();

        int width = getWidth() - padddingleft - padright;
        int height = getHeight() - padtop - padbottom;

        canvas.drawRect(0+padddingleft, 0+padtop, width+padddingleft, height+padtop, mPaint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);//获取宽的modle
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);//获取高的modle
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);//获取宽的size
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        //判断模式类型，进行尺寸设置
        if(widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST){
            //如果都是自适应
            setMeasuredDimension(ComUtils.dp2px(getContext(),100), ComUtils.dp2px(getContext(), 100));//这里的单位是px
        }else if(widthSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(ComUtils.dp2px(getContext(), 300), heightSpecSize);
        }else if(heightSpecMode == MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize, ComUtils.dp2px(getContext(), 150));
        }



    }
}
