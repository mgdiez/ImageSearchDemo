package com.marcgdiez.imagesearchdemo.app.gallery.di;

import com.marcgdiez.imagesearchdemo.app.gallery.SearchGalleryFragment;
import com.marcgdiez.imagesearchdemo.core.di.PerFragment;
import dagger.Subcomponent;

@PerFragment @Subcomponent(modules = SearchGalleryModule.class)
public interface SearchGalleryComponent {
  void inject(SearchGalleryFragment fragment);
}