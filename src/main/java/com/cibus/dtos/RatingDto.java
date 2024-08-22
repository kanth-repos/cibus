package com.cibus.dtos;

import java.io.Serializable;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

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
  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long userId;

  @NotNull(groups = {CreateGroup.class, UpdateGroup.class})
  private Long foodId;
  
  @NotNull(groups = {CreateGroup.class})
  @Min(value=1, groups = {CreateGroup.class, UpdateGroup.class})
  @Max(value=5, groups = {CreateGroup.class, UpdateGroup.class})
  private Integer rating;
  
  @NotNull(groups = {CreateGroup.class})
  @NotBlank(groups = {CreateGroup.class})
  private String message;
}
