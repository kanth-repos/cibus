package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.common.dtos.RatingDto;
import com.cibus.common.models.RatingModel;

public interface IRatingRepository {
  ArrayList<RatingModel> getRatingsByHotelId(long hotelId) throws Exception;
  RatingModel addRating(RatingDto rating) throws Exception;
  void updateRating(RatingModel rating) throws Exception;
  void deleteRating(RatingModel rating) throws Exception;
  void deleteRating(long userId, long foodId) throws Exception;
}
