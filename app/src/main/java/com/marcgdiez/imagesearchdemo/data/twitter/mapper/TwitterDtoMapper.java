package com.marcgdiez.imagesearchdemo.data.twitter.mapper;

import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

public class TwitterDtoMapper {

  @Inject public TwitterDtoMapper() {
  }

  public List<ImageEntity> toEntity(List<String> imageUrls) {
    List<ImageEntity> images = null;
    if (imageUrls != null) {
      images = new ArrayList<>();
      for (String imageUrl : imageUrls) {
        images.add(new ImageEntity(imageUrl));
      }
    }
    return images;
  }
}
