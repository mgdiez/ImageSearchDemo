package com.marcgdiez.imagesearchdemo.data.repository;

import com.marcgdiez.imagesearchdemo.data.flickr.FlickrNetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.twitter.TwitterNetworkDataSource;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;
import rx.schedulers.Schedulers;

public class ImagesRepositoryImpl implements ImagesRepository {

  private final FlickrNetworkDataSource flickerDataSource;
  private final TwitterNetworkDataSource twitterNetworkDataSource;

  @Inject public ImagesRepositoryImpl(FlickrNetworkDataSource flickerDataSource,
      TwitterNetworkDataSource twitterNetworkDataSource) {

    if (flickerDataSource == null || twitterNetworkDataSource == null) {
      throw new IllegalArgumentException("ImagesRepository parameters can't be null");
    }

    this.flickerDataSource = flickerDataSource;
    this.twitterNetworkDataSource = twitterNetworkDataSource;
  }

  @Override public Observable<List<ImageEntity>> getImages(String query) {
    //Different subscribeOn to perform both calls at same time
    return Observable.concat(flickerDataSource.searchImages(query)
        .onErrorResumeNext(Observable.empty())
        .subscribeOn(Schedulers.io()), twitterNetworkDataSource.searchImages(query)
        .onErrorResumeNext(Observable.empty())
        .subscribeOn(Schedulers.io()));
  }
}
