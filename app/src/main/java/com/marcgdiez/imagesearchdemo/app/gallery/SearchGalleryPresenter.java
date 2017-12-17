package com.marcgdiez.imagesearchdemo.app.gallery;

import com.marcgdiez.imagesearchdemo.app.gallery.usecase.SearchImagesUseCase;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesState;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.core.interactor.Interactor;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import rx.Subscriber;

public class SearchGalleryPresenter extends Presenter<SearchGalleryView> {

  private SearchImagesStoryController storyController;
  private SearchImagesUseCase searchImagesUseCase;

  @Inject public SearchGalleryPresenter(SearchImagesStoryController storyController,
      Interactor<List<ImageEntity>> searchImagesUseCase) {
    this.storyController = storyController;
    this.searchImagesUseCase = (SearchImagesUseCase) searchImagesUseCase;
  }

  @Override protected void initialize() {
    SearchImagesState storyState = storyController.getStoryState();

    if (storyState != null) {
      List<ImageEntity> imageList = storyState.getImageList();
      if (imageList != null) {
        view.showData(imageList);
      }
    }
  }

  @Override public void stop() {
    searchImagesUseCase.unsubscribe();
  }

  public void onQuerySubmitted(String query) {
    view.showProgress();

    searchImagesUseCase.execute(query, new Subscriber<List<ImageEntity>>() {
      List<ImageEntity> images = new ArrayList<>();

      @Override public void onCompleted() {
        unsubscribe();
        view.hideProgress();
        if (images.isEmpty()) {
          view.showNoResults();
        } else {
          storyController.getStoryState().setImageList(images);
          view.showData(images);
        }
      }

      @Override public void onError(Throwable e) {
        view.showError();
      }

      @Override public void onNext(List<ImageEntity> imageEntities) {
        images.addAll(imageEntities);
      }
    });
  }
}
