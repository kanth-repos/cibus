package com.cibus.common.dtos;

public class RatingDto {
  // Java Bean for Rating
  public void setUserId(long userId) { 
    this.userId = userId; 
  }

  public void setFoodId(long foodId) { 
    this.foodId = foodId; 
  }

  public void setRating(int rating) { 
    this.rating = rating; 
  }

  public void setMessage(String message) { 
    this.message = message; 
  }

  public long getUserId() { 
    return userId; 
  }

  public long getFoodId() { 
    return foodId; 
  }

  public int getRating() { 
    return rating; 
  }

  public String getMessage() { 
    return message; 
  }

  // Model attributes for Rating
  private long userId;
  private long foodId;
  private int rating;
  private String message;    
}
