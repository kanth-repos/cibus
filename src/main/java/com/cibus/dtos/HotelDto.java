package com.cibus.dtos;

import java.io.Serializable;

public class HotelDto implements Serializable {
  // Java Bean for Hotel
  public void setOwnerId(Long ownerId) {
    this.ownerId = ownerId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setCity(String city) {
    this.city = city;
  }

  public Long getOwnerId() {
    return ownerId;
  }

  public String getName() {
    return name;
  }

  public String getCity() {
    return city;
  }

  private String name;
  private String city;
  private Long ownerId;
}
