package com.ys.memoryleak.fragment;

import android.os.Bundle;
import android.view.View;
import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentTransaction;

import com.ys.memoryleak.R;

/**
 * Created by yinshan on 2020/6/1.
 */
public class PresenterLeakActivity extends FragmentActivity {

  private PresenterLeakFragment mPresenterLeakFragment;

  @Override
  protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_presenter_leak);
    findViewById(R.id.btn_open_fragment).setOnClickListener(v -> {
      addFragment();
    });
  }

  private void addFragment(){
    mPresenterLeakFragment = new PresenterLeakFragment();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.add(android.R.id.content, mPresenterLeakFragment);
    ft.commitAllowingStateLoss();
  }

  @Override
  public void onBackPressed() {
    //super.onBackPressed();
    FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
    ft.remove(mPresenterLeakFragment);
    ft.commitAllowingStateLoss();
  }
}
