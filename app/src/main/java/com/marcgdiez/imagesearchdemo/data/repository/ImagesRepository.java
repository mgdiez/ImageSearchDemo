package com.marcgdiez.imagesearchdemo.data.repository;

import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import rx.Observable;

public interface ImagesRepository {

  Observable<List<ImageEntity>> getFlickerImages(String query);

  Observable<List<ImageEntity>> getTwitterImages(String query);
}
