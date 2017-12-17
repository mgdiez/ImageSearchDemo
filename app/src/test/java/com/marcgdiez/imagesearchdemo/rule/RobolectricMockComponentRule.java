package com.marcgdiez.imagesearchdemo.rule;

import com.marcgdiez.imagesearchdemo.app.di.component.ApplicationComponent;
import com.marcgdiez.imagesearchdemo.app.di.module.ApplicationModule;
import com.marcgdiez.imagesearchdemo.ImageSearchApplication;
import it.cosenonjaviste.daggermock.DaggerMockRule;
import org.robolectric.RuntimeEnvironment;

public class RobolectricMockComponentRule extends DaggerMockRule<ApplicationComponent> {

  public RobolectricMockComponentRule() {
    super(ApplicationComponent.class, new ApplicationModule(getApp()));
    set(component -> getApp().setComponent(component));
  }

  private static ImageSearchApplication getApp() {
    return ((ImageSearchApplication) RuntimeEnvironment.application);
  }
}