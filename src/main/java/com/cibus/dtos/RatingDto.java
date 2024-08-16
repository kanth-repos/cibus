package com.cibus.dtos;

import java.io.Serializable;

public class RatingDto implements Serializable {
  // Java Bean for Rating
  public void setUserId(Long userId) {
    this.userId = userId;
  }

  public void setFoodId(Long foodId) {
    this.foodId = foodId;
  }

  public void setRating(Integer rating) {
    this.rating = rating;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Long getUserId() {
    return userId;
  }

  public Long getFoodId() {
    return foodId;
  }

  public Integer getRating() {
    return rating;
  }

  public String getMessage() {
    return message;
  }

  // Model attributes for Rating
  private Long userId;
  private Long foodId;
  private Integer rating;
  private String message;
}
