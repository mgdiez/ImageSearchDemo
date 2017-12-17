package com.marcgdiez.imagesearchdemo.app.historic;

import android.support.v4.app.Fragment;
import android.view.View;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.historic.di.SearchHistoricModule;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.core.view.RootFragment;
import javax.inject.Inject;

public class SearchHistoricFragment extends RootFragment implements SearchHistoricView {

  @Inject SearchHistoricPresenter presenter;

  public static Fragment newInstance() {
    return new SearchHistoricFragment();
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
  }

  @Override protected int getFragmentLayoutResourceId() {
    return R.layout.fragment_historic;
  }

  @Override protected void initializeView(View view) {

  }

  @Override protected void initializeInjector() {
    getComponent(ImageSearchComponent.class).searchHistoricComponent(new SearchHistoricModule())
        .inject(this);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }
}
