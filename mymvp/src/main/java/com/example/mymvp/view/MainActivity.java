package com.example.mymvp.view;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.mymvp.R;
import com.example.mymvp.presenter.WetherPresenter;

/**
 * 从视图界面出发，用户要请求数据，而Presenter是具体实现者，所以Presenter要提供方法代View的实现者调用,
 * 并且Viedw的实现中必须要有Presenter的引用， 所以MainActivity中要有WetherPresenter的引用
 * View-->Presenter
 */
public class MainActivity extends AppCompatActivity implements IWetherView {

    WetherPresenter mPresenter;
    TextView tv_main;
    ProgressDialog mDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        tv_main = (TextView) findViewById(R.id.tv_main);
        //生成Presenter。这个在Activity中的onCreate方法中，并把自身当成IWetherView注入到presenter当中
        mPresenter = new WetherPresenter(this);
        findViewById(R.id.bt_main).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPresenter.requestWetherInfo();
            }
        });
    }

    @Override
    public void onInfoUpdate(final String info) {
        Log.d("whq", "--------"+info);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tv_main.setText(info);
            }
        });
    }

    @Override
    public void showWaitingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (mDialog != null && mDialog.isShowing()){
                    mDialog.dismiss();
                }
                mDialog = ProgressDialog.show(MainActivity.this, "", "正在获取中。。。");

            }
        });
    }

    @Override
    public void dissmissWaitingDialog() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if(mDialog != null && mDialog.isShowing()){
                    mDialog.dismiss();
                }
            }
        });
    }
}
