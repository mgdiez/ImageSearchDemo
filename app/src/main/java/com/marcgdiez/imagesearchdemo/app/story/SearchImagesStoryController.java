package com.marcgdiez.imagesearchdemo.app.story;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.marcgdiez.imagesearchdemo.app.gallery.SearchGalleryFragment;
import com.marcgdiez.imagesearchdemo.app.historic.SearchHistoricFragment;

public class SearchImagesStoryController {

  public static final String STORY_STATE_KEY = "STORY_STATE_KEY";
  protected SearchImagesState storyState;
  private FragmentManager supportFragmentManager;
  private int containerId;

  public SearchImagesStoryController() {
    this.storyState = new SearchImagesState();
  }

  public void init(FragmentManager supportFragmentManager, @IdRes int containerId) {
    this.supportFragmentManager = supportFragmentManager;
    this.containerId = containerId;
  }

  public void saveState(Bundle outState) {
    if (outState != null) {
      outState.putParcelable(STORY_STATE_KEY, storyState);
    }
  }

  public void restoreState(Bundle savedState) {
    if (savedState != null && savedState.containsKey(STORY_STATE_KEY)) {
      storyState = savedState.getParcelable(STORY_STATE_KEY);
    }
  }

  public SearchImagesState getStoryState() {
    return storyState;
  }

  public void addFragment(@NonNull Fragment fragment) {
    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
    fragmentTransaction.add(containerId, fragment);
    fragmentTransaction.commit();
  }

  public void replaceFragment(@NonNull Fragment fragment) {
    String fragmentName = fragment.getClass().getSimpleName();
    FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
    fragmentTransaction.replace(containerId, fragment, fragmentName);
    fragmentTransaction.addToBackStack(fragmentName);
    fragmentTransaction.commit();
  }

  public void navigateToSearchGallery() {
    addFragment(SearchGalleryFragment.newInstance());
  }

  public void navigateToSearchHistoric() {
    replaceFragment(SearchHistoricFragment.newInstance());
  }
}
