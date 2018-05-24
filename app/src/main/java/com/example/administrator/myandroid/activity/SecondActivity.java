package com.example.administrator.myandroid.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;

import com.example.administrator.myandroid.BaseActivity;
import com.example.administrator.myandroid.R;

import java.util.ArrayList;

/**
 * Created by whq on 2018/5/24.
 */

public class SecondActivity extends BaseActivity {

    ArrayList<String> mList = new ArrayList<>();

    private DrawerLayout mDrawlayout;
    private ViewPager viewPager;
    private TabLayout mTablayout;
    private Toolbar mToolbar;

    @Override
    public int getContentView() {
        return R.layout.activity_second;
    }

    @Override
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_second);
        viewPager = (ViewPager) findViewById(R.id.vp_second);
        mTablayout = (TabLayout) findViewById(R.id.tabs_second);

    }

    @Override
    public void initData() {
        setSupportActionBar(mToolbar);
        for(int i=1; i<12;i++){
            mList.add("新闻" +i);
            mTablayout.addTab(mTablayout.newTab().setText(mList.get(i)));
        }

    }

    @Override
    public void initListener() {

    }
}
