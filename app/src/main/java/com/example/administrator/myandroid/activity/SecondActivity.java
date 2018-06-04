package com.example.administrator.myandroid.activity;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.example.administrator.myandroid.BaseActivity;
import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.adapter.FragmentAdapter;
import com.example.administrator.myandroid.fragment.ListFragment;

import java.util.ArrayList;

/**
 * Created by whq on 2018/5/24.
 */

public class SecondActivity extends BaseActivity {

    ArrayList<Fragment> fragments = new ArrayList<>();
    ArrayList<String> titles = new ArrayList<>();

    private DrawerLayout mDrawlayout;
    private ViewPager mViewPager;
    private TabLayout mTablayout;
    private Toolbar mToolbar;
    ImageView iv_second_bartitile;

    @Override
    public int getContentView() {
        return R.layout.activity_second;
    }

    @Override
    public void initView() {
        mToolbar = (Toolbar) findViewById(R.id.tb_second);
        mViewPager = (ViewPager) findViewById(R.id.vp_second);
        mTablayout = (TabLayout) findViewById(R.id.tabs_second);
        iv_second_bartitile = (ImageView) findViewById(R.id.iv_second_bartitile);

    }

    @Override
    public void initData() {
        setSupportActionBar(mToolbar);
        titles.add("精选");
        titles.add("体育");
        titles.add("巴萨");
        titles.add("购物");
        titles.add("明星");
        titles.add("视频");
        titles.add("健康");
        titles.add("励志");
        titles.add("图文");
        titles.add("本地");
        titles.add("动漫");
        titles.add("搞笑");
        titles.add("精选");
        for (int i = 0; i < titles.size(); i++) {
            mTablayout.addTab(mTablayout.newTab().setText(titles.get(i)));
            fragments.add(new ListFragment());
        }

        FragmentAdapter mFragmentAdapter = new FragmentAdapter(getSupportFragmentManager(), fragments,
                titles);
        //给ViewPager设置适配器
        mViewPager.setAdapter(mFragmentAdapter);
        //将TabLayout和ViewPager关联起来
        mTablayout.setupWithViewPager(mViewPager);
        //给TabLayout设置适配器
        mTablayout.setTabsFromPagerAdapter(mFragmentAdapter);

    }

    @Override
    public void initListener() {
        mToolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    startActivity(new Intent(mContext, ThreeActivity.class));
            }
        });
        iv_second_bartitile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mContext, ThreeActivity.class));
            }
        });
    }
}
