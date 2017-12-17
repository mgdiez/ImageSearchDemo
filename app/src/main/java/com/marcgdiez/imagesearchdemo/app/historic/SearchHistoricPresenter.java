package com.marcgdiez.imagesearchdemo.app.historic;

import com.marcgdiez.imagesearchdemo.app.story.SearchImagesState;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.core.presenter.Presenter;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import java.util.List;

public class SearchHistoricPresenter extends Presenter<SearchHistoricView> {

  private SearchImagesStoryController storyController;

  public SearchHistoricPresenter(SearchImagesStoryController storyController) {
    this.storyController = storyController;
  }

  @Override protected void initialize() {
    SearchImagesState storyState = storyController.getStoryState();
    if (storyState != null) {
      List<HistoricEntity> historics = storyState.getHistorics();
      if (historics == null || historics.isEmpty()) {
        view.showNoData();
      } else {
        view.showData(historics);
      }
    } else {
      view.showNoData();
    }
  }

  @Override public void stop() {
    //Do nothing
  }
}
