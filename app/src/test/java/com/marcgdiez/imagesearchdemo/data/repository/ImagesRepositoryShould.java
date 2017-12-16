package com.marcgdiez.imagesearchdemo.data.repository;

import com.marcgdiez.imagesearchdemo.data.flickr.FlickrNetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.twitter.TwitterNetworkDataSource;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ImagesRepositoryShould {

  private FlickrNetworkDataSource flickerDataSource;
  private TwitterNetworkDataSource twitterNetworkDataSource;
  private ImagesRepository imagesRepository;

  private TestSubscriber<List<ImageEntity>> testSubscriber;

  @Before public void setUp() {
    flickerDataSource = mock(FlickrNetworkDataSource.class);
    twitterNetworkDataSource = mock(TwitterNetworkDataSource.class);
    imagesRepository = new ImagesRepositoryImpl(flickerDataSource, twitterNetworkDataSource);
    testSubscriber = TestSubscriber.create();
  }

  @Test(expected = IllegalArgumentException.class)
  public void throw_exception_with_invalid_flickr_datasource() {
    new ImagesRepositoryImpl(null, twitterNetworkDataSource);
  }

  @Test(expected = IllegalArgumentException.class)
  public void throw_exception_with_invalid_twitter_datasource() {
    new ImagesRepositoryImpl(flickerDataSource, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void throw_exception_with_invalid_arguments() {
    new ImagesRepositoryImpl(null, null);
  }

  @Test public void avoid_break_flow_if_flickr_gives_error() {
    List<ImageEntity> fakeImages = createFakeImages();

    when(flickerDataSource.searchImages(anyString())).thenReturn(
        Observable.error(new IOException()));
    when(twitterNetworkDataSource.searchImages(anyString())).thenReturn(
        Observable.just(fakeImages));

    imagesRepository.getImages("Foo").subscribe(testSubscriber);

    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValue(fakeImages);
  }

  @Test public void avoid_break_flow_if_twitter_gives_error() {
    List<ImageEntity> fakeImages = createFakeImages();

    when(flickerDataSource.searchImages(anyString())).thenReturn(Observable.just(fakeImages));
    when(twitterNetworkDataSource.searchImages(anyString())).thenReturn(
        Observable.error(new IOException()));

    imagesRepository.getImages("Foo").subscribe(testSubscriber);

    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValue(fakeImages);
  }

  @Test public void merge_both_results() {
    List<ImageEntity> flickrImages = new ArrayList<>();
    flickrImages.add(new ImageEntity("flickerUrl-1"));
    flickrImages.add(new ImageEntity("flickerUrl-2"));
    flickrImages.add(new ImageEntity("flickerUrl-3"));

    List<ImageEntity> twitterImages = new ArrayList<>();
    twitterImages.add(new ImageEntity("twitterUrl-1"));
    twitterImages.add(new ImageEntity("twitterUrl-2"));
    twitterImages.add(new ImageEntity("twitterUrl-3"));

    when(flickerDataSource.searchImages(anyString())).thenReturn(Observable.just(flickrImages));
    when(twitterNetworkDataSource.searchImages(anyString())).thenReturn(
        Observable.just(twitterImages));

    imagesRepository.getImages("Foo").subscribe(testSubscriber);

    testSubscriber.awaitTerminalEvent();
    testSubscriber.assertNoErrors();
    testSubscriber.assertCompleted();
    testSubscriber.assertValueCount(2);
  }

  private List<ImageEntity> createFakeImages() {
    List<ImageEntity> imageEntities = new ArrayList<>();
    imageEntities.add(new ImageEntity("fakeUrl1"));
    imageEntities.add(new ImageEntity("fakeUrl2"));
    imageEntities.add(new ImageEntity("fakeUrl3"));
    return imageEntities;
  }
}