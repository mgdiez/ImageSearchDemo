package com.marcgdiez.imagesearchdemo.app.story;

import android.os.Parcel;
import android.os.Parcelable;
import com.marcgdiez.imagesearchdemo.entity.HistoricEntity;
import com.marcgdiez.imagesearchdemo.entity.ImageEntity;
import java.util.ArrayList;
import java.util.List;

public class SearchImagesState implements Parcelable {

  private List<ImageEntity> imageList;
  private List<HistoricEntity> historics;

  public SearchImagesState() {
    historics = new ArrayList<>();
  }

  public void setImageList(List<ImageEntity> imageList) {
    this.imageList = imageList;
  }

  public List<ImageEntity> getImageList() {
    return imageList;
  }

  public List<HistoricEntity> getHistorics() {
    return historics;
  }

  public void addQuery(HistoricEntity historicEntity) {
    historics.add(historicEntity);
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeTypedList(this.imageList);
    dest.writeList(this.historics);
  }

  protected SearchImagesState(Parcel in) {
    this.imageList = in.createTypedArrayList(ImageEntity.CREATOR);
    this.historics = new ArrayList<>();
    in.readList(this.historics, HistoricEntity.class.getClassLoader());
  }

  public static final Creator<SearchImagesState> CREATOR = new Creator<SearchImagesState>() {
    @Override public SearchImagesState createFromParcel(Parcel source) {
      return new SearchImagesState(source);
    }

    @Override public SearchImagesState[] newArray(int size) {
      return new SearchImagesState[size];
    }
  };
}
