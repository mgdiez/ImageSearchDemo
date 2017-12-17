package com.marcgdiez.imagesearchdemo;

import android.app.Application;
import com.marcgdiez.imagesearchdemo.app.di.component.ApplicationComponent;
import com.marcgdiez.imagesearchdemo.app.di.component.DaggerApplicationComponent;
import com.marcgdiez.imagesearchdemo.app.di.module.ApplicationModule;
import com.marcgdiez.imagesearchdemo.core.di.HasComponent;

public class ImageSearchApplication extends Application
    implements HasComponent<ApplicationComponent> {

  protected ApplicationComponent applicationComponent;

  @Override public void onCreate() {
    super.onCreate();
    initializeInjector();
  }

  @Override public ApplicationComponent getComponent() {
    return applicationComponent;
  }

  private void initializeInjector() {
    applicationComponent =
        DaggerApplicationComponent.builder().applicationModule(new ApplicationModule(this)).build();
    applicationComponent.inject(this);
  }

  public void setComponent(ApplicationComponent component) {
    this.applicationComponent = component;
  }
}
