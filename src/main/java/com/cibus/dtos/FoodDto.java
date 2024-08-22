package com.cibus.dtos;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

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
  @NotNull(groups = {CreateGroup.class})
  private String name;

  @NotNull(groups = {CreateGroup.class})
  @Min(value=0, groups = {CreateGroup.class, UpdateGroup.class})
  private Integer price;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long hotelId;
}
