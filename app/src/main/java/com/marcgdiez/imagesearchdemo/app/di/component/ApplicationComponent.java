package com.marcgdiez.imagesearchdemo.app.di.component;

import com.marcgdiez.imagesearchdemo.app.di.module.ApplicationModule;
import com.marcgdiez.imagesearchdemo.core.ImageSearchApplication;
import dagger.Component;
import javax.inject.Singleton;

@Singleton @Component(modules = ApplicationModule.class) public interface ApplicationComponent {

  void inject(ImageSearchApplication application);
}
