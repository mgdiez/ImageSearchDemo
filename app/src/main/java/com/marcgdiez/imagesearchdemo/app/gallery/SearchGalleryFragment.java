package com.marcgdiez.imagesearchdemo.app.gallery;

import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import butterknife.BindView;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.gallery.adapter.ImageAdapter;
import com.marcgdiez.imagesearchdemo.app.gallery.di.SearchGalleryModule;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.core.view.RootFragment;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;

public class SearchGalleryFragment extends RootFragment implements SearchGalleryView {

  @Inject SearchGalleryPresenter presenter;
  @Inject ImageAdapter adapter;

  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  @BindView(R.id.progressBar) ContentLoadingProgressBar progressBar;

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
    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(ImageAdapter.NUMBER_OF_COLUMNS,
        StaggeredGridLayoutManager.VERTICAL));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
  }

  @Override protected void initializeInjector() {
    getComponent(ImageSearchComponent.class).searchGalleryComponent(new SearchGalleryModule())
        .inject(this);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }

}
