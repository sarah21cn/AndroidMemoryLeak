package com.ys.memoryleak.activity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yinshan on 2020/6/2.
 */
public class AnonymousSingleton {

  private List<SingletonListener> mListeners;

  private AnonymousSingleton(){
    mListeners = new ArrayList<>();
  }

  private static class Holder{
    private static AnonymousSingleton sInstance = new AnonymousSingleton();
  }

  public static AnonymousSingleton getInstance(){
    return Holder.sInstance;
  }

  public void registerListener(SingletonListener listener){
    mListeners.add(listener);
  }

  public void unregisterListener(SingletonListener listener){
    mListeners.remove(listener);
  }

  interface SingletonListener{
    void onListener();
  }
}
