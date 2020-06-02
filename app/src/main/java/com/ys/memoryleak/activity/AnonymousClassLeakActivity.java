package com.ys.memoryleak.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * Created by yinshan on 2020/6/2.
 */
public class AnonymousClassLeakActivity extends AppCompatActivity {

  private AnonymousSingleton.SingletonListener mSingletonListener;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    leak0();
  }

  // 匿名内部类持有外部类的引用，存在内存泄漏
  private void leak0(){
    mSingletonListener = new AnonymousSingleton.SingletonListener() {
      @Override
      public void onListener() {
        // do nothing
      }
    };
    AnonymousSingleton.getInstance().registerListener(mSingletonListener);
  }

  // 使用lambda表达式，不会出现内存泄漏
  private void noLeak(){
    AnonymousSingleton.getInstance().registerListener(() -> {
    });
  }

  @Override
  protected void onDestroy() {
    super.onDestroy();
    // 在destroy中unregister，可以解决内存泄漏
    AnonymousSingleton.getInstance().unregisterListener(mSingletonListener);
  }
}
