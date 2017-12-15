package com.marcgdiez.imagesearchdemo.core.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class RootFragment extends Fragment {

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    initializeInjector();
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    int fragmentLayoutResourceId = getFragmentLayoutResourceId();
    if (fragmentLayoutResourceId < 1) {
      throw new IllegalArgumentException("Fragment must have a valid layout resource Id");
    }

    return inflater.inflate(fragmentLayoutResourceId, container, false);
  }

  @LayoutRes protected abstract int getFragmentLayoutResourceId();

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializeView(view);
  }

  protected abstract void initializeView(View view);

  protected abstract void initializeInjector();
}