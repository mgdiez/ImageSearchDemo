package com.marcgdiez.imagesearchdemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class ImageEntity implements Parcelable {
  private final String url;

  public ImageEntity(String url) {
    this.url = url;
  }

  public String getUrl() {
    return url;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.url);
  }

  protected ImageEntity(Parcel in) {
    this.url = in.readString();
  }

  public static final Parcelable.Creator<ImageEntity> CREATOR =
      new Parcelable.Creator<ImageEntity>() {
        @Override public ImageEntity createFromParcel(Parcel source) {
          return new ImageEntity(source);
        }

        @Override public ImageEntity[] newArray(int size) {
          return new ImageEntity[size];
        }
      };
}
