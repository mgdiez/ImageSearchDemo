package com.marcgdiez.imagesearchdemo.app.gallery.usecase;

import com.marcgdiez.imagesearchdemo.core.executor.MainThread;
import com.marcgdiez.imagesearchdemo.data.repository.ImagesRepository;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import com.marcgdiez.imagesearchdemo.rule.ImmediateSchedulersTestRule;
import java.util.List;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import org.junit.Rule;
import org.junit.Test;
import rx.Observable;
import rx.observers.TestSubscriber;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class SearchImagesUseCaseShould {
  @Rule public ImmediateSchedulersTestRule testRule = new ImmediateSchedulersTestRule();

  @Test(expected = IllegalArgumentException.class)
  public void throw_exception_with_invalid_parameters() {
    new SearchImagesUseCase(null, null, null);
  }

  @Test public void call_repository_to_get_images() {
    ImagesRepository mockRepository = mock(ImagesRepository.class);
    Executor fakeExecutor = Executors.newSingleThreadExecutor();
    MainThread mainThread = new MainThread();
    when(mockRepository.getImages(anyString())).thenReturn(Observable.empty());

    TestSubscriber<List<ImageEntity>> subscriber = TestSubscriber.create();

    SearchImagesUseCase useCase = new SearchImagesUseCase(fakeExecutor, mainThread, mockRepository);
    useCase.execute("Foo", subscriber);
    subscriber.awaitTerminalEvent();

    verify(mockRepository).getImages("Foo");
  }
}