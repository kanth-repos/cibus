package com.cibus.models;

import java.io.Serializable;

public class RatingModel implements Serializable {
  public void setRating(int rating) {
    this.rating = rating;
  }

  public void setMessage(String message) {
    this.message = message;
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

  public int getRating() {
    return rating;
  }

  public String getMessage() {
    return message;
  }

  public long getId() {
    return id;
  }

  public RatingModel(long id) {
    this.id = id;
  }

  // Model attributes for Rating
  private long id;
  private long userId;
  private long foodId;
  private int rating;
  private String message;
}
