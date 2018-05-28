package com.example.administrator.myandroid;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by whq on 2018/5/24.
 */

public abstract class BaseActivity extends AppCompatActivity {
    public abstract int getContentView();

    public abstract void initView();

    public abstract void initData();

    public abstract void initListener();


    public Context mContext;

    public BaseActivity() {
        mContext = this;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getContentView());
        initView();
        initData();
        initListener();
    }
}
