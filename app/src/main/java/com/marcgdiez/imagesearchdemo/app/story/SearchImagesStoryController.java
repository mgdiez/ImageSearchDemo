package com.marcgdiez.imagesearchdemo.app.story;

import android.os.Bundle;

public class SearchImagesStoryController {

  public static final String STORY_STATE_KEY = "STORY_STATE_KEY";
  protected SearchImagesState storyState;

  public SearchImagesStoryController() {
    this.storyState = new SearchImagesState();
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
}
