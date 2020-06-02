package com.ys.memoryleak.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.ys.memoryleak.R;

/**
 * Created by yinshan on 2020/6/1.
 */
public class PresenterLeakFragment extends Fragment {

  private Button mButton;

  @Nullable
  @Override
  public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_presenter_leak, container, false);
    initViews(view);
    return view;
  }

  private void initViews(View view){
    mButton = view.findViewById(R.id.btn_presenter);
    new PresenterLeakPresenter(mButton);
  }
}
