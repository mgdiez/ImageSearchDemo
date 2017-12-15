package com.marcgdiez.imagesearchdemo.app.di.module;

import android.support.v7.app.AppCompatActivity;
import com.marcgdiez.imagesearchdemo.core.di.PerActivity;
import com.marcgdiez.imagesearchdemo.core.view.RootActivity;
import dagger.Module;
import dagger.Provides;

@Module public class ActivityModule {

  private final AppCompatActivity activity;

  public ActivityModule(RootActivity activity) {
    this.activity = activity;
  }

  @Provides @PerActivity public AppCompatActivity provideActivity() {
    return activity;
  }
}
