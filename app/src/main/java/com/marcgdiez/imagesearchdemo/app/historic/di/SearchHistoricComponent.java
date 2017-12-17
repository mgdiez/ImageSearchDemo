package com.marcgdiez.imagesearchdemo.app.historic.di;

import com.marcgdiez.imagesearchdemo.app.historic.SearchHistoricFragment;
import com.marcgdiez.imagesearchdemo.core.di.PerFragment;
import dagger.Subcomponent;

@PerFragment @Subcomponent(modules = SearchHistoricModule.class)
public interface SearchHistoricComponent {
  void inject(SearchHistoricFragment fragment);
}