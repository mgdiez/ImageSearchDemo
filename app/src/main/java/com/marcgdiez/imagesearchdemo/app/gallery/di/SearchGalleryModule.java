package com.marcgdiez.imagesearchdemo.app.gallery.di;

import com.marcgdiez.imagesearchdemo.app.gallery.usecase.SearchImagesUseCase;
import com.marcgdiez.imagesearchdemo.core.di.PerFragment;
import com.marcgdiez.imagesearchdemo.core.interactor.Interactor;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import dagger.Module;
import dagger.Provides;
import java.util.List;

@Module public class SearchGalleryModule {

  @Provides @PerFragment public Interactor<List<ImageEntity>> provideSearchImagesUseCase(
      SearchImagesUseCase searchImagesUseCase) {
    return searchImagesUseCase;
  }
}