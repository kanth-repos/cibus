package com.cibus.dtos;

import java.io.Serializable;

public class FoodDto implements Serializable {
  // Java Bean for Food
  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public long getHotelId() {
    return hotelId;
  }

  public String getName() {
    return name;
  }

  public int getPrice() {
    return price;
  }

  // Model attributes for Food
  private String name;
  private int price;
  private long hotelId;
}
