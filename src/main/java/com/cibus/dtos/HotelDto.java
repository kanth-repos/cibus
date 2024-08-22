package com.cibus.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

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

  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class})
  private String name;

  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class})
  private String city;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long ownerId;
}
