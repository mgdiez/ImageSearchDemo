package com.marcgdiez.imagesearchdemo.app.gallery;

import com.marcgdiez.imagesearchdemo.app.gallery.usecase.SearchImagesUseCase;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesState;
import com.marcgdiez.imagesearchdemo.app.story.SearchImagesStoryController;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import rx.Subscriber;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyListOf;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchGalleryPresenterShould {

  @Mock SearchImagesUseCase mockSearchImagesUseCase;
  @Mock SearchImagesStoryController mockStoryController;
  @Mock SearchGalleryView mockView;
  private SearchGalleryPresenter presenter;

  @Before public void setUp() {
    MockitoAnnotations.initMocks(this);

    presenter = new SearchGalleryPresenter(mockStoryController, mockSearchImagesUseCase);
    presenter.setView(mockView);
  }

  @Test public void unsubscribe_every_use_case_on_stop() {
    presenter.stop();
    verify(mockSearchImagesUseCase).unsubscribe();
  }

  @Test public void check_story_state_on_initialize() {
    presenter.start();
    verify(mockStoryController).getStoryState();
  }

  @Test public void show_empty_state_when_needed() {
    when(mockStoryController.getStoryState()).thenReturn(mock(SearchImagesState.class));

    presenter.start();

    verify(mockView).showEmptyState();
  }

  @Test public void show_images_on_initialize_if_has() {
    SearchImagesState mockSearchImagesState = Mockito.mock(SearchImagesState.class);
    when(mockStoryController.getStoryState()).thenReturn(mockSearchImagesState);
    when(mockSearchImagesState.getImageList()).thenReturn(createFakeImages());

    presenter.start();

    verify(mockView).showData(anyListOf(ImageEntity.class));
  }

  @Test public void show_progress_on_query_submitted() {
    presenter.start();
    presenter.onQuerySubmitted("Foo");

    verify(mockView).showProgress();
  }

  @Test public void show_no_results_on_complete_without_images() {
    when(mockStoryController.getStoryState()).thenReturn(new SearchImagesState());

    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(mockView).showNoResults();
  }

  @Test public void hide_progress_on_completed() {
    when(mockStoryController.getStoryState()).thenReturn(new SearchImagesState());

    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(mockView).hideProgress();
  }

  @Test public void save_query_to_state() {
    SearchImagesState searchImagesState = Mockito.mock(SearchImagesState.class);

    when(mockStoryController.getStoryState()).thenReturn(searchImagesState);

    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onNext(createFakeImages());
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(searchImagesState).addQuery(any(HistoricEntity.class));
  }

  @Test public void show_data_on_completed_with_images() {
    List<ImageEntity> fakeImages = createFakeImages();
    when(mockStoryController.getStoryState()).thenReturn(new SearchImagesState());

    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onNext(fakeImages);
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(mockView).showData(fakeImages);
  }

  @Test public void show_error_feedback_if_needed() {
    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onError(new Throwable());
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(mockView).showError();
  }

  @Test public void concat_results_until_on_completed() {
    when(mockStoryController.getStoryState()).thenReturn(new SearchImagesState());

    List<ImageEntity> fakeImages1 = createFakeImages();

    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onNext(fakeImages1);
      ((Subscriber) invocation.getArguments()[1]).onNext(fakeImages1);
      ((Subscriber) invocation.getArguments()[1]).onNext(fakeImages1);
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(mockView).showData(anyListOf(ImageEntity.class));
  }

  @Test public void save_to_state_images_retrieved() {
    SearchImagesState mockImagesState = Mockito.mock(SearchImagesState.class);
    when(mockStoryController.getStoryState()).thenReturn(mockImagesState);
    List<ImageEntity> fakeImages1 = createFakeImages();

    doAnswer(invocation -> {
      ((Subscriber) invocation.getArguments()[1]).onNext(fakeImages1);
      ((Subscriber) invocation.getArguments()[1]).onCompleted();
      return null;
    }).when(mockSearchImagesUseCase).execute(anyString(), any(Subscriber.class));

    presenter.start();
    presenter.onQuerySubmitted("FooQuery");

    verify(mockImagesState).setImageList(anyListOf(ImageEntity.class));
  }

  private List<ImageEntity> createFakeImages() {
    List<ImageEntity> imageEntities = new ArrayList<>();
    imageEntities.add(new ImageEntity("fakeUrl1"));
    imageEntities.add(new ImageEntity("fakeUrl2"));
    imageEntities.add(new ImageEntity("fakeUrl3"));
    return imageEntities;
  }
}