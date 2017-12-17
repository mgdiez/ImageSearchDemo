package com.marcgdiez.imagesearchdemo.app.di.module;

import android.content.Context;
import com.marcgdiez.imagesearchdemo.ImageSearchApplication;
import com.marcgdiez.imagesearchdemo.core.executor.MainThread;
import com.marcgdiez.imagesearchdemo.core.executor.ThreadExecutor;
import com.marcgdiez.imagesearchdemo.data.ServiceFactory;
import com.marcgdiez.imagesearchdemo.data.flickr.FlickrApi;
import com.marcgdiez.imagesearchdemo.data.flickr.FlickrApiConstants;
import com.marcgdiez.imagesearchdemo.data.flickr.FlickrNetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.repository.ImagesRepository;
import com.marcgdiez.imagesearchdemo.data.repository.ImagesRepositoryImpl;
import com.marcgdiez.imagesearchdemo.data.twitter.TwitterNetworkDataSource;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import javax.inject.Singleton;

@Module public class ApplicationModule {

  private final Context applicationContext;

  public ApplicationModule(ImageSearchApplication demoApplication) {
    applicationContext = demoApplication.getApplicationContext();
  }

  @Provides @Singleton public Executor provideExecutor() {
    return new ThreadExecutor();
  }

  @Provides @Singleton public MainThread providesMainThread() {
    return new MainThread();
  }

  @Provides @Singleton
  public ImagesRepository provideImagesRepository(FlickrNetworkDataSource flickrDataSource,
      TwitterNetworkDataSource twitterNetworkDataSource) {
    return new ImagesRepositoryImpl(flickrDataSource, twitterNetworkDataSource);
  }

  @Provides @Singleton public FlickrApi provideFlickrApi() {
    return ServiceFactory.createRetrofitService(FlickrApi.class, FlickrApiConstants.BASE_URL);
  }

  @Provides @Singleton public Context provideApplicationContext() {
    return applicationContext;
  }

}