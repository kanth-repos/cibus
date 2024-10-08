package com.cibus.models;

import java.io.Serializable;

public class FoodModel implements Serializable {
  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public long getHotelId() {
    return hotelId;
  }

  public long getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }

  public FoodModel(long id) {
    this.id = id;
  }

  // Model attributes for Food
  private long id;
  private String name;
  private int price;
  private long hotelId;
}
