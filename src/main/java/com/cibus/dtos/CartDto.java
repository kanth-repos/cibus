package com.cibus.dtos;

import java.io.Serializable;

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
  private Long userId;
  private Long foodId;
  private int quantity;
}
