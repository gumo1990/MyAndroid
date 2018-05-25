package com.example.administrator.myandroid.activity;

import android.animation.ObjectAnimator;
import android.view.animation.AnimationUtils;

import com.example.administrator.myandroid.BaseActivity;
import com.example.administrator.myandroid.R;
import com.example.administrator.myandroid.viewss.CustomView;

/**
 * Created by whq on 2018/5/25.
 */

public class ThreeActivity extends BaseActivity {
    CustomView ct_second;
    @Override
    public int getContentView() {
        return R.layout.activity_three;
    }

    @Override
    public void initView() {
        ct_second = (CustomView) findViewById(R.id.ct_second);
    }

    @Override
    public void initData() {
        //这种方式如果是button点击并没有反应，因为对于系统来说button并没有改变位置，
        // 只有点击原来位置，才会触发点击事件，所以Android 3.0后使用属性动画，不仅可以执行动画，还可以改变view的位置参数
     //   ct_second.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.translate));
        ObjectAnimator.ofFloat(ct_second, "translationX", 0, 300).setDuration(1000).start();
    }

    @Override
    public void initListener() {

    }
}
