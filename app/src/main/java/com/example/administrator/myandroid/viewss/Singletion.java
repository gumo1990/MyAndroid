package com.example.administrator.myandroid.viewss;

/**
 * Created by whq on 2018/6/1.
 * 饿汉式
 */

public class Singletion {
    /*private static  Singletion single = new Singletion();
    public static Singletion getSingle(){
        return single;
    }*/
    private static Singletion singletion = new Singletion();

    private Singletion(){}
    public static Singletion getSingletion(){
        return singletion;
    }
}
