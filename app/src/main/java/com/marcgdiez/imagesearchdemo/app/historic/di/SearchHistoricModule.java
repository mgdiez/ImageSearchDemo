package com.marcgdiez.imagesearchdemo.app.historic.di;

import com.marcgdiez.imagesearchdemo.app.historic.SearchHistoricPresenter;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.core.di.PerFragment;
import dagger.Module;
import dagger.Provides;

@Module public class SearchHistoricModule {
  @Provides @PerFragment
  public SearchHistoricPresenter providePresenter(SearchImagesStoryController storyController) {
    return new SearchHistoricPresenter(storyController);
  }
}
