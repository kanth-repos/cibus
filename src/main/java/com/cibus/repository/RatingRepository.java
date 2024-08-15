package com.cibus.repository;

import java.sql.Connection;
import java.sql.Statement;
import java.util.ArrayList;

import com.cibus.dtos.RatingDto;
import com.cibus.interfaces.repository.IRatingRepository;
import com.cibus.models.RatingModel;

public class RatingRepository implements IRatingRepository {
  public RatingRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public RatingModel addRating(RatingDto rating) throws Exception {
    final var query = "INSERT INTO ratings (user_id, food_id, rating, message) VALUES (?, ?, ?, ?)";
    try (var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setLong(1, rating.getUserId());
      stmt.setLong(2, rating.getFoodId());
      stmt.setInt(3, rating.getRating());
      stmt.setString(4, rating.getMessage());
      stmt.executeUpdate();

      RatingModel model = null;

      try (var generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          model = new RatingModel(generatedKeys.getLong(1));
        } else {
          throw new Exception("Can't get Primary Key");
        }
      }

      model.setUserId(rating.getUserId());
      model.setFoodId(rating.getFoodId());
      model.setRating(rating.getRating());
      model.setMessage(rating.getMessage());

      return model;
    }
  }

  @Override
  public ArrayList<RatingModel> getRatingsByFoodId(long id) throws Exception {
    final var query = "SELECT * FROM ratings where food_id = " + id;
    try (var stmt = connection.createStatement()) {
      var rs = stmt.executeQuery(query);
      var ratings = new ArrayList<RatingModel>();

      while (rs.next()) {
        var rating = new RatingModel(rs.getLong(1));
        rating.setUserId(rs.getLong(2));
        rating.setFoodId(rs.getLong(3));
        rating.setRating(rs.getInt(4));
        rating.setMessage(rs.getString(5));
        ratings.add(rating);
      }

      return ratings;
    }
  }

  @Override
  public void updateRating(RatingModel rating) throws Exception {
    final var query = "UPDATE ratings SET rating = ?, message = ?, user_id = ?, food_id = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, rating.getRating());
      stmt.setString(2, rating.getMessage());
      stmt.setLong(3, rating.getUserId());
      stmt.setLong(4, rating.getFoodId());
      stmt.setLong(5, rating.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteRating(RatingModel rating) throws Exception {
    this.deleteRating(rating.getId());
  }

  @Override
  public void deleteRating(long id) throws Exception {
    final var query = "DELETE FROM ratings WHERE id = " + id;
    try (var stmt = connection.createStatement()) {
      stmt.executeUpdate(query);
    }
  }
}
