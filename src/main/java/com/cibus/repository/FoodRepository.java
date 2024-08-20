package com.cibus.repository;

import com.cibus.interfaces.repository.IFoodRepository;
import com.cibus.models.FoodModel;
import com.cibus.dtos.FoodDto;
import com.cibus.exceptions.RowNotFoundException;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class FoodRepository implements IFoodRepository {
  public FoodRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public FoodModel addFood(FoodDto food) throws Exception {
    final var query = "INSERT INTO foods (hotel_id, name, price) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setLong(1, food.getHotelId());
      stmt.setString(2, food.getName());
      stmt.setInt(3, food.getPrice());
      stmt.executeUpdate();

      FoodModel model = null;

      try (var generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          model = new FoodModel(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Can't get Primary Key");
        }
      }

      model.setHotelId(food.getHotelId());
      model.setPrice(food.getPrice());
      model.setName(food.getName());
      return model;
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
        var food = new FoodModel(foodId);
        food.setHotelId(rs.getLong(2));
        food.setName(rs.getString(3));
        food.setPrice(rs.getInt(4));
        foods.add(food);
      }

      return foods;
    }
  }

  @Override
  public void updateFood(FoodModel food) throws Exception {
    final var query = "UPDATE foods SET name = ?, price = ?, hotel_id = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, food.getName());
      stmt.setInt(2, food.getPrice());
      stmt.setLong(3, food.getHotelId());
      stmt.setLong(4, food.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteFood(FoodModel food) throws Exception {
    this.deleteFood(food.getId());
  }

  @Override
  public void deleteFood(long id) throws Exception {
    final var query = "DELETE FROM foods WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, id);
      stmt.executeUpdate();
    }
  }

  @Override
  public FoodModel getFood(long id) throws Exception {
    final var query = "SELECT * FROM foods where id = " + id;
    try (var stmt = connection.createStatement()) {
      var rs = stmt.executeQuery(query);

      if(!rs.next()) {
        throw new RowNotFoundException(query);
      }

      var foodId = rs.getLong(1);
      var food = new FoodModel(foodId);
      food.setHotelId(rs.getLong(2));
      food.setName(rs.getString(3));
      food.setPrice(rs.getInt(4));
      return food;
    }
  }
}
