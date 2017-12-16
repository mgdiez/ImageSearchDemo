package com.marcgdiez.imagesearchdemo.app.di.module;

import com.marcgdiez.imagesearchdemo.core.executor.MainThread;
import com.marcgdiez.imagesearchdemo.core.executor.ThreadExecutor;
import com.marcgdiez.imagesearchdemo.data.ServiceFactory;
import com.marcgdiez.imagesearchdemo.data.flickr.FlickrApi;
import com.marcgdiez.imagesearchdemo.data.flickr.FlickrApiConstants;
import com.marcgdiez.imagesearchdemo.data.flickr.FlickrNetworkDataSource;
import com.marcgdiez.imagesearchdemo.data.repository.ImagesRepository;
import com.marcgdiez.imagesearchdemo.data.repository.ImagesRepositoryImpl;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.Executor;
import javax.inject.Singleton;

@Module public class ApplicationModule {

  @Provides @Singleton public Executor provideExecutor() {
    return new ThreadExecutor();
  }

  @Provides @Singleton public MainThread providesMainThread() {
    return new MainThread();
  }

  @Provides @Singleton
  public ImagesRepository provideImagesRepository(FlickrNetworkDataSource flickrDataSource) {
    return new ImagesRepositoryImpl(flickrDataSource);
  }

  @Provides @Singleton public FlickrApi provideFlickrApi() {
    return ServiceFactory.createRetrofitService(FlickrApi.class, FlickrApiConstants.BASE_URL);
  }
}