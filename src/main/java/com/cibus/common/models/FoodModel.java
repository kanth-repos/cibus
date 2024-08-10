package com.cibus.common.models;

public class FoodModel {
  public void setName(String name) { 
    this.name = name; 
  }

  public void setPrice(int price) { 
    this.price = price; 
  }

  public long getHotelId() { 
    return hotelId; 
  }

  public long getId() { 
    return id; 
  }

  public String getName() { 
    return name; 
  }

  public int getPrice() { 
    return price; 
  }

  public FoodModel(long id, long hotelId) {
    this.id = id;
    this.hotelId = hotelId;
  }

  // Model attributes for Food
  private long id;
  private String name;
  private int price;
  private long hotelId;  
}
