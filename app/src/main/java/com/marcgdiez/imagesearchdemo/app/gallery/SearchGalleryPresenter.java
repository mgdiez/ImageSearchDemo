package com.marcgdiez.imagesearchdemo.app.gallery;

import com.marcgdiez.imagesearchdemo.app.gallery.usecase.SearchImagesUseCase;
import com.marcgdiez.imagesearchdemo.core.interactor.Interactor;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;

public class SearchGalleryPresenter extends Presenter<SearchGalleryView> {
  private SearchImagesUseCase searchImagesUseCase;

  @Inject public SearchGalleryPresenter(Interactor<List<ImageEntity>> searchImagesUseCase) {
    this.searchImagesUseCase = (SearchImagesUseCase) searchImagesUseCase;
  }

  @Override protected void initialize() {

  }

  @Override public void stop() {
    searchImagesUseCase.unsubscribe();
  }
}
