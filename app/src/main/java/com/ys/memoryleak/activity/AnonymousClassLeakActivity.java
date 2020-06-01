package com.ys.memoryleak.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * Created by yinshan on 2020/6/1.
 */
public class AnonymousClassLeakActivity extends Activity {

  private static final String TAG = "AnonymoutClassLeak:";

  private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
      if(msg.what == 0){
        Log.d(TAG, "onReceive message");
      }
    }
  };

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    new Thread(){
      @Override
      public void run() {
        try{
          Thread.sleep(10000);
          mHandler.sendEmptyMessage(0);
        }catch (Exception e){
          e.printStackTrace();
        }
      }
    }.start();
  }
}
