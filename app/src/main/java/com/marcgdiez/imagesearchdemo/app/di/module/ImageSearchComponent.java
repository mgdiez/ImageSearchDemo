package com.marcgdiez.imagesearchdemo.app.di.module;

import com.marcgdiez.imagesearchdemo.app.MainActivity;
import com.marcgdiez.imagesearchdemo.app.gallery.di.SearchGalleryComponent;
import com.marcgdiez.imagesearchdemo.app.gallery.di.SearchGalleryModule;
import com.marcgdiez.imagesearchdemo.core.di.PerActivity;
import dagger.Subcomponent;

@PerActivity @Subcomponent(modules = ImageSearchModule.class)
public interface ImageSearchComponent {
  void inject(MainActivity activity);

  SearchGalleryComponent searchGalleryComponent(SearchGalleryModule module);
}