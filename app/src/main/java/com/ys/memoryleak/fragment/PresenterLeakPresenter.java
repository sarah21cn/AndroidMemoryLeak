package com.ys.memoryleak.fragment;

import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import androidx.annotation.NonNull;

import com.ys.memoryleak.R;

/**
 * Created by yinshan on 2020/6/2.
 */
public class PresenterLeakPresenter {

  private PresenterLeakFragment mLeakFragment;
  private Button btnPresenter;

  private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
    }
  };

  public PresenterLeakPresenter(Button button) {
//    PresenterLeakFragment会泄漏
//    this.mLeakFragment = fragment;
//    if (mLeakFragment.getView() != null) {
//      btnPresenter = mLeakFragment.getView().findViewById(R.id.btn_presenter);
//    }

    // button会泄漏，提示View detached and has parent
    this.btnPresenter = button;
    mHandler.sendEmptyMessageDelayed(0, 60000);
  }
}
