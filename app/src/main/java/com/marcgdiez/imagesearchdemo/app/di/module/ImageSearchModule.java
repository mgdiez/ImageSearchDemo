package com.marcgdiez.imagesearchdemo.app.di.module;

import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.core.di.PerActivity;
import dagger.Module;
import dagger.Provides;

@Module public class ImageSearchModule {
  @Provides @PerActivity public SearchImagesStoryController provideSearchImagesStoryController() {
    return new SearchImagesStoryController();
  }
}
