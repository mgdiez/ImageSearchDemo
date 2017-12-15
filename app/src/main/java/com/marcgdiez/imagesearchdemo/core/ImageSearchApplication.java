package com.marcgdiez.imagesearchdemo.core;

import android.app.Application;
import com.marcgdiez.imagesearchdemo.app.di.component.ApplicationComponent;
import com.marcgdiez.imagesearchdemo.core.di.HasComponent;

public class ImageSearchApplication extends Application
    implements HasComponent<ApplicationComponent> {

  protected ApplicationComponent applicationComponent;

  @Override public ApplicationComponent getComponent() {
    return applicationComponent;
  }
}
