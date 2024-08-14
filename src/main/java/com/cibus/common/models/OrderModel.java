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

  public OrderModel(long id, long userId, long foodId) {
    this.id = id;
    this.userId = userId;
    this.foodId = foodId;
  }

  // Model attributes for Order
  private long id;
  private long userId;
  private long foodId;
  private int quantity;  
}
