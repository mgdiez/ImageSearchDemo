package com.marcgdiez.imagesearchdemo.data.repository;

import com.marcgdiez.imagesearchdemo.data.flickr.FlickrNetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.twitter.TwitterNetworkDataSource;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class ImagesRepositoryImpl implements ImagesRepository {

  private final FlickrNetworkDataSource flickerDataSource;
  private final TwitterNetworkDataSource twitterNetworkDataSource;

  @Inject public ImagesRepositoryImpl(FlickrNetworkDataSource flickerDataSource,
      TwitterNetworkDataSource twitterNetworkDataSource) {
    this.flickerDataSource = flickerDataSource;
    this.twitterNetworkDataSource = twitterNetworkDataSource;
  }

  @Override public Observable<List<ImageEntity>> getFlickerImages(String query) {
    return flickerDataSource.searchImages(query);
  }

  @Override public Observable<List<ImageEntity>> getTwitterImages(String query) {
    return twitterNetworkDataSource.searchImages(query);
  }
}
