package com.marcgdiez.imagesearchdemo.app.di.component;

import com.marcgdiez.imagesearchdemo.app.di.module.ApplicationModule;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchModule;
import com.marcgdiez.imagesearchdemo.ImageSearchApplication;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(ImageSearchApplication application);

  // Sub components generation
  ImageSearchComponent imageSearchComponent(ImageSearchModule imageSearchModule);
}
