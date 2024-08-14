package com.cibus.common.models;

import java.io.Serializable;

public class CartModel implements Serializable {
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

  // get immutable properties via constructor
  public CartModel(long userId, long foodId) {
    this.userId = userId;
    this.foodId = foodId;
  }

  // Model attributes for Cart
  private long userId;
  private long foodId;
  private int quantity;  
}
