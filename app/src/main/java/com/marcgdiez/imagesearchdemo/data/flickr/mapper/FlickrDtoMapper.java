package com.marcgdiez.imagesearchdemo.data.flickr.mapper;

import com.marcgdiez.imagesearchdemo.data.flickr.dto.FlickrDto;
import com.marcgdiez.imagesearchdemo.data.flickr.dto.FlickrMainResponseDto;
import com.marcgdiez.imagesearchdemo.data.flickr.dto.FlickrResponseDto;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class FlickrDtoMapper {

  @Inject public FlickrDtoMapper() {
  }

  public List<ImageEntity> toEntity(FlickrMainResponseDto flickrMainResponseDto) {
    List<ImageEntity> images = null;
    if (flickrMainResponseDto != null) {
      FlickrResponseDto flickrResponseDto = flickrMainResponseDto.getPhotos();
      if (flickrResponseDto != null) {
        List<FlickrDto> photo = flickrResponseDto.getPhoto();
        if (photo != null) {
          images = new ArrayList<>();
          for (FlickrDto flickrDto : photo) {
            ImageEntity imageEntity = new ImageEntity(createFlickrUrl(flickrDto));
            images.add(imageEntity);
          }
        }
      }
    }
    return images;
  }

  //https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}_[mstzb].jpg
  private String createFlickrUrl(FlickrDto flickrDto) {
    return "https://farm" + flickrDto.getFarm() + ".staticflickr.com/" + String.valueOf(
        flickrDto.getServer()) + "/" + flickrDto.getId() + "_" + flickrDto.getSecret() + "_m.jpg";
  }
}
