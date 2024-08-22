package com.cibus.dtos;

import java.io.Serializable;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

public class OrderDto implements Serializable {
  // Java Bean for Order
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setFoodId(Long foodId) {
    this.foodId = foodId;
  }

  public void setQuantity(Integer quantity) {
    this.quantity = quantity;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getFoodId() {
    return foodId;
  }

  public Integer getQuantity() {
    return quantity;
  }

  // Model attributes for Order
  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long userId;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long foodId;

  @NotNull(groups = {CreateGroup.class})
  @Min(value=1, groups = {CreateGroup.class, UpdateGroup.class})
  private Integer quantity;
}
