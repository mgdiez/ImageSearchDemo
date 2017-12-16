package com.marcgdiez.imagesearchdemo.app.gallery;

import android.support.v4.app.Fragment;
import android.view.View;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.gallery.di.SearchGalleryModule;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.core.view.RootFragment;
import javax.inject.Inject;

public class SearchGalleryFragment extends RootFragment implements SearchGalleryView {

  @Inject SearchGalleryPresenter presenter;

  public static Fragment newInstance() {
    return new SearchGalleryFragment();
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
  }

  @Override protected int getFragmentLayoutResourceId() {
    return R.layout.fragment_search_gallery;
  }

  @Override protected void initializeView(View view) {

  }

  @Override protected void initializeInjector() {
    getComponent(ImageSearchComponent.class).searchGalleryComponent(new SearchGalleryModule())
        .inject(this);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }
}
