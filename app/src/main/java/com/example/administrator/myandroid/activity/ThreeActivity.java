package com.example.administrator.myandroid.activity;

import android.animation.ObjectAnimator;
import android.content.Intent;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.Toast;

import com.example.administrator.myandroid.BaseActivity;
import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.bean.MessageEvent;
import com.example.administrator.myandroid.utils.ComUtils;
import com.example.administrator.myandroid.viewss.CustomView;
import com.example.administrator.myandroid.viewss.TitleBar;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/**
 * Created by whq on 2018/5/25.
 * EventBus的4中ThreadMode（线程模型）
 * A:  posting  （默认）发布和接收事件在同一个线程中--避免耗时操作，会阻塞事件传递，甚至ANR
 * B:  main，     事件处理在UI线程，处理时间不能过长，否则ANR
 * C:  background 如果事件是在UI线程发布的，处理函数会在新线程中运行，如果是在子线程中发布的，就在子线程中执行，事件
 * 处理函数中禁止进行UI跟新操作
 * D:  async:   无论在哪个线程发布，该事件处理函数都会在新建的子线程中执行，该事件处理函数中禁止UI跟新操作
 */

public class ThreeActivity extends BaseActivity {
    CustomView ct_second;
    TitleBar title_bar;
    Button bt_three;
    @Override
    public int getContentView() {
        return R.layout.activity_three;
    }

    @Override
    public void initView() {
        ct_second = (CustomView) findViewById(R.id.ct_second);
        title_bar = (TitleBar) findViewById(R.id.title_bar);
        bt_three = (Button) findViewById(R.id.bt_three);
    }

    @Override
    public void initData() {
       /* 这种方式如果是button点击并没有反应，因为对于系统来说button并没有改变位置，
         只有点击原来位置，才会触发点击事件，所以Android 3.0后使用属性动画，不仅可以执行动画，还可以改变view的位置参数
        ct_second.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.translate));*/
    //    ObjectAnimator.ofFloat(ct_second, "translationX", 0, 300).setDuration(1000).start();//使用属性动画
        title_bar.setTitle("二次定义标题名称");
    }

    @Override
    public void initListener() {
        title_bar.setLeftTitleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            //    ct_second.smoothScrollTo(0, 0);
            }
        });
        title_bar.setRightTitleListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //    ct_second.smoothScrollTo(-400, 0);
             //   ComUtils.toast(mContext, "右侧图片点击");
                startActivity(new Intent(mContext, FourActivity.class));
            //    EventBus.getDefault().postSticky(new MessageEvent("A跳转B"));
            }
        });
        bt_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //注册事件
                EventBus.getDefault().register(mContext);
            }
        });
    }

    /**
     * 接收EventBus中返回来的消息,方法名称可以自定义
     * 方法必须是public
     */
    //@Subscribe(threadMode = ThreadMode.POSTING , sticky = true)//黏性事件，可以先跳转下一页面，再次回来后才注册也行
    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onMooEvent(MessageEvent messageEvent){
        Log.d("whq", "====="+messageEvent.getMessage());
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //取消注册事件
        EventBus.getDefault().unregister(mContext);
    }
}
