package com.cibus.common.dtos;

import java.io.Serializable;

public class OrderDto implements Serializable {
  // Java Bean for Order
  public void setUserId(long userId) { 
    this.userId = userId; 
  }

  public void setFoodId(long foodId) { 
    this.foodId = foodId; 
  }

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

  // Model attributes for Order
  private long userId;
  private long foodId;
  private int quantity;  
}
