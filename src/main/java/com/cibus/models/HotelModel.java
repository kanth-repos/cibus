package com.cibus.models;

import java.io.Serializable;

public class HotelModel implements Serializable {
  public void setName(String name) {
    this.name = name;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public long getOwnerId() {
    return ownerId;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  public void setOwnerId(long id) {
    this.ownerId = id;
  }

  public HotelModel(long id) {
    this.id = id;
  }

  // Model attributes for Hotel
  private long id;
  private String name;
  private String city;
  private long ownerId;
}
