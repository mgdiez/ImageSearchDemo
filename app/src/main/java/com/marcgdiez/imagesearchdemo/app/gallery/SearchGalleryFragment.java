package com.marcgdiez.imagesearchdemo.app.gallery;

import android.support.v4.app.Fragment;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
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
  @BindView(R.id.feedback) TextView feedback;

  private MenuItem searchItem;

  public static Fragment newInstance() {
    return new SearchGalleryFragment();
  }

  @Override protected void initializePresenter() {
    presenter.setView(this);
  }

  @Override protected int getFragmentLayoutResourceId() {
    return R.layout.fragment_search_gallery;
  }

  @Override protected boolean hasToolbar() {
    return false;
  }

  @Override protected void initializeView(View view) {
    setHasOptionsMenu(true);
    recyclerView.setLayoutManager(new StaggeredGridLayoutManager(ImageAdapter.NUMBER_OF_COLUMNS,
        StaggeredGridLayoutManager.VERTICAL));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    if (item.getItemId() == R.id.historic) {
      presenter.onHistoricClicked();
    }
    return super.onOptionsItemSelected(item);
  }

  @Override public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
    inflater.inflate(R.menu.menu, menu);
    searchItem = menu.findItem(R.id.search);
    ((SearchView) searchItem.getActionView()).setOnQueryTextListener(
        new SearchView.OnQueryTextListener() {
          @Override public boolean onQueryTextSubmit(String query) {
            presenter.onQuerySubmitted(query);
            return false;
          }

          @Override public boolean onQueryTextChange(String newText) {
            return false;
          }
        });
  }

  @Override protected void initializeInjector() {
    getComponent(ImageSearchComponent.class).searchGalleryComponent(new SearchGalleryModule())
        .inject(this);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }

  @Override public void showData(List<ImageEntity> images) {
    recyclerView.setVisibility(View.VISIBLE);
    adapter.setItems(images);
  }

  @Override public void showProgress() {
    progressBar.setVisibility(View.VISIBLE);
    recyclerView.setVisibility(View.GONE);
    feedback.setVisibility(View.GONE);
  }

  @Override public void hideProgress() {
    progressBar.setVisibility(View.GONE);
    feedback.setVisibility(View.GONE);
  }

  @Override public void showError() {
    feedback.setText(R.string.error_message);
    feedback.setVisibility(View.VISIBLE);
  }

  @Override public void showNoResults() {
    feedback.setText(R.string.no_results);
    feedback.setVisibility(View.VISIBLE);
  }

  @Override public void showEmptyState() {
    feedback.setText(R.string.empty_state);
    feedback.setVisibility(View.VISIBLE);
  }
}
