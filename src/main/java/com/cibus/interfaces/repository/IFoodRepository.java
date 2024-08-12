package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.common.dtos.FoodDto;
import com.cibus.common.models.FoodModel;

public interface IFoodRepository {
  ArrayList<FoodModel> getFoodsByHotelId(long hotelId) throws Exception;

  void addFood(FoodDto food) throws Exception;
  void updateFood(FoodModel food) throws Exception;
  void deleteFood(FoodModel food) throws Exception;
}
