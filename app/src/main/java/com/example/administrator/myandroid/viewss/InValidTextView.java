package com.example.administrator.myandroid.viewss;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by whq on 2018/5/28.
 * 自定义TextView，继承系统控件，需要重写OnDraw方法
 */

public class InValidTextView extends TextView {
    Paint paint = new Paint(Paint.STRIKE_THRU_TEXT_FLAG);//加中划线

    public InValidTextView(Context context) {
        super(context);
        initDraw();
    }

    public InValidTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        initDraw();
    }

    public InValidTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initDraw();
    }

    /**
     * 设置画笔
     */
    private void initDraw(){
        paint.setColor(Color.RED);
        paint.setStrokeWidth((float) 1.5);
    }

    /**
     * 布置画布
     */
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int width = getWidth();
        int height = getHeight();
        canvas.drawLine(0, height/2, width, height/2, paint);
    }
}
