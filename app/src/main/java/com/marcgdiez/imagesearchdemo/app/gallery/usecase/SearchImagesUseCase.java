package com.marcgdiez.imagesearchdemo.app.gallery.usecase;

import com.marcgdiez.imagesearchdemo.core.executor.MainThread;
import com.marcgdiez.imagesearchdemo.core.interactor.Interactor;
import com.marcgdiez.imagesearchdemo.data.repository.ImagesRepository;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import java.util.concurrent.Executor;
import javax.inject.Inject;
import rx.Observable;
import rx.Subscriber;

public class SearchImagesUseCase extends Interactor<List<ImageEntity>> {

  private final ImagesRepository imagesRepository;
  private String query;

  @Inject public SearchImagesUseCase(Executor executor, MainThread mainThread,
      ImagesRepository imagesRepository) {
    super(executor, mainThread);

    if (imagesRepository == null) {
      throw new IllegalArgumentException("SearchImagesUseCase must have valid parameters");
    }

    this.imagesRepository = imagesRepository;
  }

  public void execute(String query, Subscriber<List<ImageEntity>> subscriber) {
    this.query = query;
    super.execute(subscriber);
  }

  @Override protected Observable<List<ImageEntity>> buildObservable() {
    return imagesRepository.getImages(query);
  }
}
