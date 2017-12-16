package com.marcgdiez.imagesearchdemo.data.flickr;

import com.marcgdiez.imagesearchdemo.data.flickr.dto.FlickrMainResponseDto;
import com.marcgdiez.imagesearchdemo.data.flickr.dto.FlickrResponseDto;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

public interface FlickrApi {

  String KEY = "efa050875079a8b702936cc34d44b37f";

  @GET("?method=flickr.photos.search&nojsoncallback=1&format=json&api_key=" + KEY)
  Observable<FlickrMainResponseDto> search(@Query("text") String query);
}