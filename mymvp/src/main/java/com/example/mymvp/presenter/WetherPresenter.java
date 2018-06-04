package com.example.mymvp.presenter;

import android.util.Log;

import com.example.mymvp.model.IWetherImple;
import com.example.mymvp.view.IWetherView;

/**
 * Created by whq on 2018/6/1.
 * Presenter是个大忙人，因为同时要对View和model对接，所以内部必须持有他们的接口引用,
 * Presenter 也要开发API供View调用,所以要有requestWetherInfo()方法
 * presenter--->View  presenter操作View是通过View.interface，也就是View层定义的接口，
 * 所以得到下面1,2,3方法
 * presetner-->Model presenter获取到了数据，可以交给Model处理
 * Model -->presenter
 */

public class WetherPresenter {
    IWetherImple mModel;
    IWetherView mView;

    //供View层调用，用来请求天气数据
    //Presenter本身需要向服务器获取代码，所以还要编写它的相应方法
    public void requestWetherInfo() {
        getNetWorkInfo();

    }

    //因为Presenter持有View的引用，所以将View.interface注入到Presenter中
    public WetherPresenter(IWetherView mView) {
        this.mView = mView;
        mModel = new IWetherImple();
    }

    //1
    private void showWaitingDialog() {
        if (mView != null) {
            mView.showWaitingDialog();
        }
    }

    //2
    private void dismissWaitingDialog() {
        if (mView != null) {
            mView.dissmissWaitingDialog();
        }
    }

    //3
    private void updateWetherInfo(String info) {
        if (mView != null) {
            mView.onInfoUpdate(info);
        }
    }

    //presenter获取到了数据，可以交给Model处理
    private void saveInfo(String info) {
        mModel.setInfo(info);
    }

    //Model处理完数据后也能对Presenter提供数据
    private String localInfo() {
        return mModel.getInfo();
    }

    private void getNetWorkInfo() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //打开等待对话框
                    showWaitingDialog();
                    //模拟网络耗时
                    Thread.sleep(3000);
                    String info = "21度，晴转多云";
                    //保存到Model层
                    saveInfo(info);
                    //从Model层获取数据，为了演示效果，实际开发中根据情况需要。
                    String localinfo = localInfo();
                    //通知View层改变视图
                    updateWetherInfo(info);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    //取消对话框
                    dismissWaitingDialog();
                }
            }
        }).start();
    }

}
