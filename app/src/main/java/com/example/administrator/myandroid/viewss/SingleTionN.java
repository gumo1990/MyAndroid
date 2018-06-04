package com.example.administrator.myandroid.viewss;

/**
 * Created by whq on 2018/6/1.
 * 匿名内部类
 */

public class SingleTionN {

  /*  private static class SingleHolder{
        private static final SingleTionN s = new SingleTionN();

    }

    public static final SingleTionN getSingleTionN(){
        return SingleHolder.s;
    }*/

    private static class SingleN{
        private static final SingleTionN s = new SingleTionN();
    }

    private SingleTionN(){}

    public static SingleTionN getSingleN(){
        return  SingleN.s;
    }
}
