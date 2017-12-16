package com.marcgdiez.imagesearchdemo.data.repository;

import com.marcgdiez.imagesearchdemo.data.NetworkDataSource;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class ImagesRepositoryImpl implements ImagesRepository {

  private final NetworkDataSource flickerDataSource;

  @Inject public ImagesRepositoryImpl(NetworkDataSource flickerDataSource) {
    this.flickerDataSource = flickerDataSource;
  }

  @Override public Observable<List<ImageEntity>> getFlickerImages(String query) {
    return flickerDataSource.searchImages(query);
  }
}
