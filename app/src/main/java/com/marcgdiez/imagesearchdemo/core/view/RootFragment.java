package com.marcgdiez.imagesearchdemo.core.view;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;

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

  protected abstract void initializePresenter();

  @LayoutRes protected abstract int getFragmentLayoutResourceId();

  @Override public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    initializeView(view);
    initializePresenter();
  }

  protected abstract void initializeView(View view);

  protected abstract void initializeInjector();

  @Override public void onResume() {
    super.onResume();

    Presenter presenter = getPresenter();
    if (presenter != null) {
      presenter.start();
    }
  }

  @Override public void onStop() {
    super.onStop();

    Presenter presenter = getPresenter();
    if (presenter != null) {
      presenter.stop();
    }
  }

  protected abstract Presenter getPresenter();
}