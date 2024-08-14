package com.cibus.common.models;

import java.io.Serializable;

public class OrderModel implements Serializable {
  public void setQuantity(int quantity) { 
    this.quantity = quantity; 
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

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public void setFoodId(long foodId) {
    this.foodId = foodId;
  }

  public OrderModel(long id) {
    this.id = id;
  }

  // Model attributes for Order
  private long id;
  private long userId;
  private long foodId;
  private int quantity;  
}
