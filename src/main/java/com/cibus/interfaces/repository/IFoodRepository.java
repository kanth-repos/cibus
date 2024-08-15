package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.dtos.FoodDto;
import com.cibus.models.FoodModel;

public interface IFoodRepository {
  ArrayList<FoodModel> getFoodsByHotelId(long hotelId) throws Exception;

  FoodModel addFood(FoodDto food) throws Exception;
  void updateFood(FoodModel food) throws Exception;
  void deleteFood(FoodModel food) throws Exception;
  void deleteFood(long id) throws Exception;
}
