package com.cibus.repository;

import com.cibus.interfaces.repository.IFoodRepository;
import com.cibus.common.dtos.FoodDto;
import com.cibus.common.models.FoodModel;

import java.sql.Connection;
import java.util.ArrayList;


public class FoodRepository implements IFoodRepository {
  public FoodRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addFood(FoodDto food) throws Exception {
    final var query = "INSERT INTO foods (hotel_id, name, price) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, food.getHotelId());
      stmt.setString(2, food.getName());
      stmt.setInt(3, food.getPrice());
      stmt.executeUpdate();
    }
  }

  @Override
  public ArrayList<FoodModel> getFoodsByHotelId(long id) throws Exception {
    final var query = "SELECT * FROM foods where hotel_id = " + id;
    try (var stmt = connection.createStatement()) {
      var foods = new ArrayList<FoodModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var foodId = rs.getLong(1);
        var hotelId = rs.getLong(2);
        var food = new FoodModel(foodId, hotelId);
        food.setName(rs.getString(3));
        food.setPrice(rs.getInt(4));
        foods.add(food);
      }

      return foods;
    }
  }

  @Override
  public void updateFood(FoodModel food) throws Exception {
    final var query = "UPDATE foods SET name = ?, price = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, food.getName());
      stmt.setInt(2, food.getPrice());
      stmt.setLong(3, food.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteFood(FoodModel food) throws Exception {
    final var query = "DELETE FROM foods WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, food.getId());
      stmt.executeUpdate();
    }
  }
}
