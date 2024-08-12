package com.cibus.common.models;

public class RatingModel {
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

  public RatingModel(long userId, long foodId) {
    this.userId = userId;
    this.foodId = foodId;
  }

  // Model attributes for Rating
  private long userId;
  private long foodId;
  private int rating;
  private String message;    
}
