package com.marcgdiez.imagesearchdemo.data.flickr.dto;

import java.util.List;

public class FlickrResponseDto {
  private List<FlickrDto> photo;

  public List<FlickrDto> getPhoto() {
    return photo;
  }

  public void setPhoto(List<FlickrDto> photo) {
    this.photo = photo;
  }
}
