package com.ys.memoryleak.activity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.ys.memoryleak.R;

import java.lang.ref.WeakReference;
import java.util.concurrent.TimeUnit;

/**
 * Created by yinshan on 2020/6/1.
 */
public class HandlerLeakActivity extends Activity {

  private static final String TAG = "AnonymoutClassLeak:";
  private static final long DELAY_IN_MILLIS = TimeUnit.SECONDS.toMillis(60);

  private TextView textView;
  private InnerHandler mInnerHandler;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_anonymous_class_leak);
    textView = findViewById(R.id.text_view);
    leak0();
  }

  // 内部类InnerHandler会持有外部类（Activity）的引用
  private void leak0(){
    mInnerHandler = new InnerHandler();
    mInnerHandler.sendEmptyMessageDelayed(0, DELAY_IN_MILLIS);
  }

  // 虽然使用static handler，但是直接引用view，会导致activity无法被回收
  private void leak1(){
    new InnerStaticHandler(textView).sendEmptyMessageDelayed(0,  DELAY_IN_MILLIS);
  }

  // 使用static handler，同时传入view的weakreference，可以解决内存泄漏的问题
  private void noLeak(){
    new InnerStaticWeakHandler(textView).sendEmptyMessageDelayed(0, DELAY_IN_MILLIS);
  }

  private class InnerHandler extends Handler{
    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
    }
  }

  private static class InnerStaticHandler extends Handler{

    private View view;

    public InnerStaticHandler(View view) {
      this.view = view;
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
    }
  }

  private static class InnerStaticWeakHandler extends Handler{

    private WeakReference<View> vRef;

    public InnerStaticWeakHandler(View view){
      vRef = new WeakReference<>(view);
    }

    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
    }
  }

  @Override
  protected void onStop() {
    super.onStop();
    // 关闭activity的时候清除handler上的message，可以解决内存泄漏的问题
    // solutionForInnerHandler();
  }

  private void solutionForInnerHandler(){
    mInnerHandler.removeMessages(0);
    mInnerHandler.removeCallbacksAndMessages(null);
  }
}
