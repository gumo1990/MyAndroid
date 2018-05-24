package com.example.administrator.myandroid.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myandroid.BaseActivity;
import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.adapter.MainAdapter;

import java.util.ArrayList;

/**
 * 1.RecyclerView使用，删除item，需要自定义分割线
 * 2.CardView继承FrameLayout，可控制边框阴影等效果
 */
public class MainActivity extends BaseActivity {

    ArrayList<String> mList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    MainAdapter adapter;

    RecyclerView rv_main;
    CardView cv_main;
    SeekBar main_sb1, main_sb2, main_sb3;
    Toolbar toolbar;
    DrawerLayout dl_main;
    TextView tv_main_left;
    FloatingActionButton fb_main;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        rv_main = (RecyclerView) findViewById(R.id.rv_main);
        cv_main = (CardView) findViewById(R.id.cv_main);
        main_sb1 = (SeekBar) findViewById(R.id.main_sb1);
        main_sb2 = (SeekBar) findViewById(R.id.main_sb2);
        main_sb3 = (SeekBar) findViewById(R.id.main_sb3);

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        dl_main = (DrawerLayout) findViewById(R.id.dl_main);
        tv_main_left = (TextView) findViewById(R.id.tv_main_left);
        fb_main = (FloatingActionButton) findViewById(R.id.fb_main);

        linearLayoutManager = new LinearLayoutManager(this);
        adapter = new MainAdapter(mContext, mList);


    }

    @Override
    public void initData() {
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv_main.setLayoutManager(linearLayoutManager);
        rv_main.setItemAnimator(new DefaultItemAnimator());
        rv_main.setAdapter(adapter);
        for (int i = 1; i < 50; i++) {
            mList.add(i + "");
        }
        tooBarSet();

    }

    /**
     * 自定义toorbar的一些设置
     */
    private void tooBarSet() {
        dl_main.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        toolbar.setTitle("Toolbar");
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        toolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(mContext, "action_setting", Toast.LENGTH_SHORT).show();
                        dl_main.openDrawer(Gravity.LEFT);
                        break;
                    case R.id.action_share:
                       startActivity(new Intent(mContext, SecondActivity.class));
                        break;
                    case R.id.aciton_search:
                        Toast.makeText(mContext, "aciton_search", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }
        });
    }

    @Override
    public void initListener() {
        adapter.setOnitemClick(new MainAdapter.OnitemClick() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(mContext, "点击了第" + (position + 1) + "条数据", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(mContext).setTitle("确认删除第" + (position + 1) + "条数据吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                adapter.remove(position);
                            }
                        }).show();
            }
        });

        main_sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cv_main.setRadius(progress);//设置圆角半径
                //  cv_main.setBackgroundColor(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        main_sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cv_main.setCardElevation(progress);//设置阴影半径
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        main_sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                cv_main.setContentPadding(progress, progress, progress, progress);//距离边框距离
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        tv_main_left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dl_main.closeDrawer(Gravity.LEFT);
            }
        });
        fb_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "buttonFloatting", Toast.LENGTH_SHORT).show();
            }
        });
    }


}
