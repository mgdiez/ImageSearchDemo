package com.marcgdiez.imagesearchdemo.data.twitter;

import com.marcgdiez.imagesearchdemo.data.NetworkDataSource;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import rx.Observable;

public class TwitterNetworkDataSource implements NetworkDataSource {

  @Override public Observable<List<ImageEntity>> searchImages(String query) {
    return null;
  }
}
