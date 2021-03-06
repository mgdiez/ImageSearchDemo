package com.marcgdiez.imagesearchdemo.app;

import android.os.Bundle;
import android.view.MenuItem;
import com.marcgdiez.imagesearchdemo.R;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchComponent;
import com.marcgdiez.imagesearchdemo.app.di.module.ImageSearchModule;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.ImageSearchApplication;
import com.marcgdiez.imagesearchdemo.core.di.HasComponent;
import com.marcgdiez.imagesearchdemo.core.view.RootActivity;
import javax.inject.Inject;

public class MainActivity extends RootActivity implements HasComponent<ImageSearchComponent> {

  private ImageSearchComponent imageSearchComponent;
  @Inject SearchImagesStoryController storyController;

  @Override protected void findViews() {
    //This activity has no views
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
    storyController.init(getSupportFragmentManager(), R.id.fragment_container);
    if (savedInstanceState == null) {
      storyController.navigateToSearchGallery();
    } else {
      storyController.restoreState(savedInstanceState);
    }
  }

  @Override public boolean onOptionsItemSelected(MenuItem item) {
    switch (item.getItemId()) {
      case android.R.id.home:
        onBackPressed();
        return true;
      default:
        return super.onOptionsItemSelected(item);
    }
  }
  @Override protected void saveState(Bundle outState) {
    storyController.saveState(outState);
  }

  @Override public ImageSearchComponent getComponent() {
    return imageSearchComponent;
  }
}
