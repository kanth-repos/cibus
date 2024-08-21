package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.dtos.RatingDto;
import com.cibus.models.RatingModel;

public interface IRatingRepository {
  ArrayList<RatingModel> getRatingsByFoodId(long foodId) throws Exception;
  ArrayList<RatingModel> getRatingsByUserId(long userId) throws Exception;
  RatingModel addRating(RatingDto rating) throws Exception;
  RatingModel getRating(long id) throws Exception;
  RatingModel getRating(long userId, long foodId) throws Exception;
  void updateRating(RatingModel rating) throws Exception;
  void deleteRating(RatingModel rating) throws Exception;
  void deleteRating(long id) throws Exception;
}
