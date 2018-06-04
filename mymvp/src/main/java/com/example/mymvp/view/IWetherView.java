package com.example.mymvp.view;

/**
 * Created by whq on 2018/6/1.
 */

public interface IWetherView {
    //显示天气信息
    public void onInfoUpdate(String info);
    //显示后去信息等待对话框
    public void showWaitingDialog();
    //取消显示对话框
    public void dissmissWaitingDialog();
}
