package com.marcgdiez.imagesearchdemo.app.di.module;

import com.marcgdiez.imagesearchdemo.core.executor.MainThread;
import com.marcgdiez.imagesearchdemo.core.executor.ThreadExecutor;
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
}