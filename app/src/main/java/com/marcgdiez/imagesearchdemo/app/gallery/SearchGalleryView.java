package com.marcgdiez.imagesearchdemo.app.gallery;

import com.marcgdiez.imagesearchdemo.core.view.IView;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;

public interface SearchGalleryView extends IView {
  void showData(List<ImageEntity> images);

  void showProgress();

  void hideProgress();

  void showError();

  void showNoResults();

  void showEmptyState();
}
