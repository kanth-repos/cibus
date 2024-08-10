package com.cibus.interfaces;

import java.util.ArrayList;

import com.cibus.common.dtos.FoodDto;
import com.cibus.common.models.FoodModel;

public interface IFoodRepository {
  ArrayList<FoodModel> getFoodsByHotelId(long hotelId);

  void addFood(FoodDto food);
  void updateFood(FoodModel food);
  void deleteFood(FoodModel food);
}
