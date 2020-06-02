package com.ys.memoryleak.activity;

import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.ys.memoryleak.MainApp;

/**
 * Created by yinshan on 2020/6/2.
 */
public class SingletonLeakActivity extends AppCompatActivity {

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    leak0();
  }


  private void leak0(){
    Singleton.getInstance(this);
  }

  private void noLeak(){
    Singleton.getInstance(MainApp.getAppContext());
  }
}
