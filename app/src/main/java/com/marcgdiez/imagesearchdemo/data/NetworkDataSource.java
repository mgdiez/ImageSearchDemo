package com.marcgdiez.imagesearchdemo.data;

import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.List;
import rx.Observable;

public interface NetworkDataSource {

  Observable<List<ImageEntity>> searchImages(String query);
}