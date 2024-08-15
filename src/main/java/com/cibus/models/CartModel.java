package com.cibus.models;

import java.io.Serializable;

public class CartModel implements Serializable {
  public void setQuantity(int quantity) {
    this.quantity = quantity;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setFoodId(long foodId) {
    this.foodId = foodId;
  }

  public long getUserId() {
    return userId;
  }

  public long getFoodId() {
    return foodId;
  }

  public int getQuantity() {
    return quantity;
  }

  public long getId() {
    return id;
  }

  // get immutable properties via constructor
  public CartModel(long id) {
    this.id = id;
  }

  // Model attributes for Cart
  private long id;
  private long userId;
  private long foodId;
  private int quantity;
}
