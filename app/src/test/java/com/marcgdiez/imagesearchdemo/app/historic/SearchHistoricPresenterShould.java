package com.marcgdiez.imagesearchdemo.app.historic;

import com.marcgdiez.imagesearchdemo.app.story.SearchImagesState;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchHistoricPresenterShould {

  @Mock SearchImagesStoryController mockStoryController;
  @Mock SearchHistoricView mockView;
  private SearchHistoricPresenter presenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);

    presenter = new SearchHistoricPresenter(mockStoryController);
    presenter.setView(mockView);
  }

  @Test public void check_story_state_on_initialize() {
    presenter.start();
    verify(mockStoryController).getStoryState();
  }

  @Test public void show_empty_state_when_no_historic() {
    when(mockStoryController.getStoryState()).thenReturn(mock(SearchImagesState.class));

    presenter.start();

    verify(mockView).showNoData();
  }

  @Test public void show_historic_on_initialize_if_has() {
    SearchImagesState mockSearchImagesState = Mockito.mock(SearchImagesState.class);
    when(mockStoryController.getStoryState()).thenReturn(mockSearchImagesState);
    List<HistoricEntity> fakeHistorics = createFakeHistorics();
    when(mockSearchImagesState.getHistorics()).thenReturn(fakeHistorics);

    presenter.start();

    verify(mockView).showData(fakeHistorics);
  }

  private List<HistoricEntity> createFakeHistorics() {
    List<HistoricEntity> historicEntities = new ArrayList<>();
    historicEntities.add(new HistoricEntity("Foo", 1));
    historicEntities.add(new HistoricEntity("Foo", 2));
    historicEntities.add(new HistoricEntity("Foo", 56));
    return historicEntities;
  }
}