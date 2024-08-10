package com.cibus.interfaces;

import java.util.ArrayList;

import com.cibus.common.dtos.RatingDto;
import com.cibus.common.models.RatingModel;

public interface IRatingRepository {
  ArrayList<RatingModel> getRatingsByHotelId(long hotelId);
  void addRating(RatingDto rating);
  void updateRating(RatingModel rating);
  void deleteRating(RatingModel rating);
}
