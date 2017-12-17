package com.marcgdiez.imagesearchdemo.entity;

import android.os.Parcel;
import android.os.Parcelable;

public class HistoricEntity implements Parcelable {

  private final String query;
  private final int nResults;

  public HistoricEntity(String query, int nResults) {
    this.query = query;
    this.nResults = nResults;
  }

  public String getQuery() {
    return query;
  }

  public int getnResults() {
    return nResults;
  }

  @Override public int describeContents() {
    return 0;
  }

  @Override public void writeToParcel(Parcel dest, int flags) {
    dest.writeString(this.query);
    dest.writeInt(this.nResults);
  }

  protected HistoricEntity(Parcel in) {
    this.query = in.readString();
    this.nResults = in.readInt();
  }

  public static final Parcelable.Creator<HistoricEntity> CREATOR =
      new Parcelable.Creator<HistoricEntity>() {
        @Override public HistoricEntity createFromParcel(Parcel source) {
          return new HistoricEntity(source);
        }

        @Override public HistoricEntity[] newArray(int size) {
          return new HistoricEntity[size];
        }
      };
}
