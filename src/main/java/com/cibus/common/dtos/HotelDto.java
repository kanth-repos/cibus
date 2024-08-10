package com.cibus.common.dtos;

public class HotelDto {
  // Java Bean for Hotel
  public void setOwnerId(long ownerId) { 
    this.ownerId = ownerId; 
  }

  public void setName(String name) { 
    this.name = name; 
  }

  public void setCity(String city) { 
    this.city = city; 
  }

  public long getOwnerId() { 
    return ownerId; 
  }

  public String getName() { 
    return name; 
  }

  public String getCity() { 
    return city; 
  }

  private String name;
  private String city;
  private long ownerId;
}
