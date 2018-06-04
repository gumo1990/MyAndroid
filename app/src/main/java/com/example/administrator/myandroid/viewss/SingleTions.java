package com.example.administrator.myandroid.viewss;

/**
 * Created by whq on 2018/6/1.
 * 懒汉式
 */

public class SingleTions {
   /* private static SingleTions singletions = null;

    public static SingleTions getSingletion(){
        if(singletions == null){
            singletions= new SingleTions();
        }
        return singletions;
    }*/

    private static SingleTions singleTions = null;

    private SingleTions(){}

    public static SingleTions getSingleTions(){
        if(singleTions == null){
            singleTions = new SingleTions();
        }
        return singleTions;
    }
}
