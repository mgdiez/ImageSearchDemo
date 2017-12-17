package com.marcgdiez.imagesearchdemo.app.historic;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import butterknife.BindView;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.historic.adapter.HistoricAdapter;
import com.marcgdiez.imagesearchdemo.app.historic.di.SearchHistoricModule;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.core.view.RootFragment;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import java.util.List;
import javax.inject.Inject;

public class SearchHistoricFragment extends RootFragment implements SearchHistoricView {

  @Inject SearchHistoricPresenter presenter;
  @Inject HistoricAdapter adapter;

  @BindView(R.id.recyclerView) RecyclerView recyclerView;
  @BindView(R.id.feedback) TextView feedback;

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
    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    recyclerView.setHasFixedSize(true);
    recyclerView.setAdapter(adapter);
  }

  @Override protected void initializeInjector() {
    getComponent(ImageSearchComponent.class).searchHistoricComponent(new SearchHistoricModule())
        .inject(this);
  }

  @Override protected Presenter getPresenter() {
    return presenter;
  }

  @Override public void showData(List<HistoricEntity> historicEntities) {
    adapter.setItems(historicEntities);
    recyclerView.setVisibility(View.VISIBLE);
    feedback.setVisibility(View.GONE);
  }

  @Override public void showNoData() {
    recyclerView.setVisibility(View.GONE);
    feedback.setVisibility(View.VISIBLE);
  }
}
