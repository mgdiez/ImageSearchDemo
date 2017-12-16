package com.marcgdiez.imagesearchdemo.app;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchModule;
import com.marcgdiez.imagesearchdemo.app.gallery.SearchGalleryFragment;
import com.marcgdiez.imagesearchdemo.core.ImageSearchApplication;
import com.marcgdiez.imagesearchdemo.core.di.HasComponent;
import com.marcgdiez.imagesearchdemo.core.view.RootActivity;

public class MainActivity extends RootActivity implements HasComponent<ImageSearchComponent> {

  private ImageSearchComponent imageSearchComponent;

  @Override protected void findViews() {

  }

  @Override protected int getLayoutResourceId() {
    return R.layout.activity_main;
  }

  @Override protected void initializeInjector() {
    imageSearchComponent = ((ImageSearchApplication) getApplication()).getComponent()
        .imageSearchComponent(new ImageSearchModule());
    imageSearchComponent.inject(this);
  }

  @Override protected void initializeActivity(Bundle savedInstanceState) {
    addFragment(R.id.fragment_container, SearchGalleryFragment.newInstance());
  }

  protected void addFragment(@IdRes int containerId, @NonNull Fragment fragment) {
    FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
    fragmentTransaction.add(containerId, fragment);
    fragmentTransaction.commit();
  }

  @Override public ImageSearchComponent getComponent() {
    return imageSearchComponent;
  }
}
