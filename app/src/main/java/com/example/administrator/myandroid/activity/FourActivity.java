package com.example.administrator.myandroid.activity;

import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.administrator.myandroid.BaseActivity;
import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.bean.MessageEvent;
import com.example.administrator.myandroid.viewss.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import static org.greenrobot.eventbus.ThreadMode.MAIN;

/**
 * Created by whq on 2018/5/29.
 */

public class FourActivity extends BaseActivity {
    TitleBar title_four_bar;
    Button bt_four;
    @Override
    public int getContentView() {
        return R.layout.activity_four;
    }

    @Override
    public void initView() {
        title_four_bar = (TitleBar) findViewById(R.id.title_four_bar);
        bt_four = (Button) findViewById(R.id.bt_four);
    //    EventBus.getDefault().register(mContext);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {
        title_four_bar.setLeftTitleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        bt_four.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EventBus.getDefault().post(new MessageEvent("event发布了"));
            }
        });
    }

 /*   @Subscribe(threadMode = ThreadMode.POSTING, sticky = true)
    public void sent(MessageEvent event){
        Log.d("whq", "0909===="+event.getMessage());
    }*/
    @Override
    protected void onDestroy() {
        super.onDestroy();
     //   EventBus.getDefault().unregister(mContext);
    }
}
