package com.ys.memoryleak.activity;

import android.content.Context;

/**
 * Created by yinshan on 2020/6/2.
 */
public class Singleton {

  private static Singleton sInstance;
  private Context mContext;

  private Singleton(Context context){
    this.mContext = context;
  }

  public static Singleton getInstance(Context context){
    if(sInstance == null){
      synchronized (Singleton.class){
        if(sInstance == null){
          sInstance = new Singleton(context);
        }
      }
    }
    return sInstance;
  }
}
