package com.marcgdiez.imagesearchdemo.data.twitter;

import com.marcgdiez.imagesearchdemo.data.NetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.twitter.mapper.TwitterDtoMapper;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import javax.inject.Inject;
import rx.Observable;

public class TwitterNetworkDataSource implements NetworkDataSource {

  private final TwitterDtoMapper twitterDtoMapper;
  private final TwitterApi twitterApi;

  @Inject
  public TwitterNetworkDataSource(TwitterDtoMapper twitterDtoMapper, TwitterApi twitterApi) {
    this.twitterDtoMapper = twitterDtoMapper;
    this.twitterApi = twitterApi;
  }

  @Override public Observable<List<ImageEntity>> searchImages(String query) {
    return twitterApi.search(query).toList().map(twitterDtoMapper::toEntity);
  }
}
