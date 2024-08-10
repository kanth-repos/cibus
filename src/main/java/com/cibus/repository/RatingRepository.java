package com.cibus.repository;

import java.sql.Connection;
import java.util.ArrayList;

import com.cibus.common.dtos.RatingDto;
import com.cibus.common.models.RatingModel;
import com.cibus.exceptions.DatabaseException;
import com.cibus.interfaces.IRatingRepository;

public class RatingRepository implements IRatingRepository {
  public RatingRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addRating(RatingDto rating) {
    final var query = "INSERT INTO ratings (user_id, food_id, rating, message) VALUES (?, ?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, rating.getUserId());
      stmt.setLong(2, rating.getFoodId());
      stmt.setInt(3, rating.getRating());
      stmt.setString(4, rating.getMessage());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public ArrayList<RatingModel> getRatingsByHotelId(long id) {
    final var query = "SELECT * FROM ratings where hotel_id = " + id;
    try (var stmt = connection.createStatement()) {
      var ratings = new ArrayList<RatingModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var userId = rs.getLong(2);
        var foodId = rs.getLong(3);
        var rating = new RatingModel(userId, foodId);
        rating.setRating(rs.getInt(4));
        rating.setMessage(rs.getString(5));
        ratings.add(rating);
      }

      return ratings;
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void updateRating(RatingModel rating) {
    final var query = "UPDATE ratings SET rating = ?, message = ? WHERE user_id = ? AND food_id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, rating.getRating());
      stmt.setString(2, rating.getMessage());
      stmt.setLong(3, rating.getUserId());
      stmt.setLong(4, rating.getFoodId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void deleteRating(RatingModel rating) {
    final var query = "DELETE FROM ratings WHERE user_id = ? AND food_id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, rating.getUserId());
      stmt.setLong(2, rating.getFoodId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}
