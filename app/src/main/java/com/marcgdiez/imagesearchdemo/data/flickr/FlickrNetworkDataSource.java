package com.marcgdiez.imagesearchdemo.data.flickr;

import com.marcgdiez.imagesearchdemo.data.NetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.flickr.mapper.FlickrDtoMapper;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class FlickrNetworkDataSource implements NetworkDataSource {

  private final FlickrApi flickrApi;
  private final FlickrDtoMapper flickrDtoMapper;

  @Inject FlickrNetworkDataSource(FlickrApi flickrApi, FlickrDtoMapper flickrDtoMapper) {
    this.flickrApi = flickrApi;
    this.flickrDtoMapper = flickrDtoMapper;
  }

  @Override public Observable<List<ImageEntity>> searchFlickerImages(String query) {
    return flickrApi.search(query).map(flickrDtoMapper::toEntity);
  }
}
