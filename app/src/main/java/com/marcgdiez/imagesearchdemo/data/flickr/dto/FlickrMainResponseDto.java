package com.marcgdiez.imagesearchdemo.data.flickr.dto;

public class FlickrMainResponseDto {
  private FlickrResponseDto photos;

  public FlickrResponseDto getPhotos() {
    return photos;
  }

  public void setPhotos(FlickrResponseDto photos) {
    this.photos = photos;
  }
}
