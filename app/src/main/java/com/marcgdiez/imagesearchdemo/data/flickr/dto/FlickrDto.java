package com.marcgdiez.imagesearchdemo.data.flickr.dto;

public class FlickrDto {
  private long id;
  private String secret;
  private int farm;
  private int server;

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getSecret() {
    return secret;
  }

  public void setSecret(String secret) {
    this.secret = secret;
  }

  public int getFarm() {
    return farm;
  }

  public void setFarm(int farm) {
    this.farm = farm;
  }

  public int getServer() {
    return server;
  }

  public void setServer(int server) {
    this.server = server;
  }
}
