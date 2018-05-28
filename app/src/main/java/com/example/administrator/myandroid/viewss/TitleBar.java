package com.example.administrator.myandroid.viewss;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.administrator.myandroid.R;

/**
 * Created by whq on 2018/5/28.
 * 自定义组合控件，一个titlebar，左右图片，中间标题
 */

public class TitleBar extends RelativeLayout {

    ImageView iv_left, iv_right;
    RelativeLayout rl_title_bar;
    TextView tv_title_bar_name;
    String tv_title_name;
    int mColor = Color.BLUE;
    int mTextColor = Color.BLACK;

    public TitleBar(Context context) {
        super(context);
        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray mType = context.obtainStyledAttributes(attrs,R.styleable.TitleBar);
        mColor = mType.getColor(R.styleable.TitleBar_title_bar_bg, Color.BLUE);
        mTextColor = mType.getColor(R.styleable.TitleBar_tv_bar_title_color, Color.GREEN);
        tv_title_name = mType.getString(R.styleable.TitleBar_tv_bar_title);
        mType.recycle();

        initView(context);
    }

    public TitleBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }

    /**
     * inflate(子布局，将要填充的父控件，是否填充到父控件中)，adapter中的第三个参数是false
     */
    private void initView(Context context) {

        LayoutInflater.from(context).inflate(R.layout.title_view, this, true);
        iv_left = findViewById(R.id.ivl_title_bar);
        iv_right = findViewById(R.id.ivr_title_bar);
        tv_title_bar_name = findViewById(R.id.tv_title_bar_name);
        rl_title_bar = findViewById(R.id.rl_title_bar);

        //设置背景颜色
        rl_title_bar.setBackgroundColor(mColor);
        //设置字体颜色
        tv_title_bar_name.setTextColor(mTextColor);
        tv_title_bar_name.setText(tv_title_name);

    }

    /**
     * 设置title
     */
    public void setTitle(String title) {
        if (!TextUtils.isEmpty(title)) {
            tv_title_bar_name.setText(title);
        }
    }

    public void setLeftTitleListener(OnClickListener onClickListener) {
        iv_left.setOnClickListener(onClickListener);
    }

    public void setRightTitleListener(OnClickListener onClickListener) {
        iv_right.setOnClickListener(onClickListener);
    }

}

