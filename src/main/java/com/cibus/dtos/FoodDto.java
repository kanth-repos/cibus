package com.cibus.dtos;

import java.io.Serializable;

public class FoodDto implements Serializable {
  // Java Bean for Food
  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setPrice(Integer price) {
    this.price = price;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public String getName() {
    return name;
  }

  public Integer getPrice() {
    return price;
  }

  // Model attributes for Food
  private String name;
  private Integer price;
  private Long hotelId;
}
