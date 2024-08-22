package com.cibus.dtos;

import java.io.Serializable;
import javax.validation.constraints.*;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

public class CartDto implements Serializable {
  // Java Bean for Cart
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

  // Model attributes for Cart
  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long userId;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long foodId;

  @NotNull(groups = {CreateGroup.class})
  @Min(value=1, groups = {CreateGroup.class, UpdateGroup.class})
  private Integer quantity;
}
