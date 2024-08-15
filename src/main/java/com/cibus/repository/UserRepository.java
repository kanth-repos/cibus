package com.cibus.repository;

import java.sql.Connection;
import java.sql.SQLException;

import com.cibus.dtos.UserDto;
import com.cibus.exceptions.UserNotFoundException;
import com.cibus.interfaces.repository.IUserRepository;
import com.cibus.models.UserModel;

public class UserRepository implements IUserRepository {
  public UserRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void updateUser(UserModel user) throws Exception {
    final var query = "UPDATE users SET type = ?, name = ?, mobile = ?, email = ?, password = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, user.getType());
      stmt.setString(2, user.getName());
      stmt.setString(3, user.getMobile());
      stmt.setString(4, user.getEmail());
      stmt.setString(5, user.getPassword());
      stmt.setLong(6, user.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public UserModel getUser(long id) throws Exception {
    final var query = "SELECT * FROM users where id = " + id;

    try (var stmt = connection.createStatement()) {
      var rs = stmt.executeQuery(query);

      if(!rs.next()) {
        throw new UserNotFoundException(String.valueOf(id));
      }

      var user = new UserModel(rs.getLong(1));
      user.setType(rs.getString(2));
      user.setName(rs.getString(3));
      user.setMobile(rs.getString(4));
      user.setEmail(rs.getString(5));
      user.setPassword(rs.getString(6));
      return user;
    }
  }

  @Override
  public UserModel getUser(String email) throws Exception {
    final var query = "SELECT * FROM users where email = ?";

    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, email);

      var rs = stmt.executeQuery();

      if(!rs.next()) {
        throw new UserNotFoundException(String.valueOf(email));
      }

      var user = new UserModel(rs.getLong(1));
      user.setType(rs.getString(2));
      user.setName(rs.getString(3));
      user.setMobile(rs.getString(4));
      user.setEmail(rs.getString(5));
      user.setPassword(rs.getString(6));
      return user;
    }
  }

  @Override
  public UserModel addUser(UserDto user) throws Exception {
    final var query = "INSERT INTO users (type, name, mobile, email, password) VALUES (?, ?, ?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, user.getType());
      stmt.setString(2, user.getName());
      stmt.setString(3, user.getMobile());
      stmt.setString(4, user.getEmail());
      stmt.setString(5, user.getPassword());
      stmt.executeUpdate();

      UserModel model = null;

      try (var generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          model = new UserModel(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Can't get Primary Key");
        }
      }

      model.setPassword(user.getPassword());
      model.setType(user.getType());
      model.setName(user.getName());
      model.setMobile(user.getMobile());
      model.setEmail(user.getEmail());

      return model;
    }
  }

  @Override
  public void deleteUser(UserModel user) throws Exception {
    this.deleteUser(user.getId());
  }

  @Override
  public void deleteUser(long id) throws Exception {
    final var query = "DELETE FROM users WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, id);
      stmt.executeUpdate();
    }
  }

  @Override
  public boolean isOwnerOfFood(long userId, long foodId) throws Exception {
    final var query = "SELECT hotel_id FROM foods WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, foodId);
      var rs = stmt.executeQuery();

      if(!rs.next()) {
        return false;
      }

      return isOwnerOfHotel(userId, rs.getLong(1));
    }
  }

  @Override
  public boolean isOwnerOfHotel(long userId, long hotelId) throws Exception {
    final var query = "SELECT * FROM hotels WHERE id = ? AND owner_id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, hotelId);
      stmt.setLong(2, userId);

      var rs = stmt.executeQuery();
      return rs.next();
    }
  }

  @Override
  public boolean isOwnerOfOrder(long userId, long orderId) throws Exception {
    final var query = "SELECT * FROM orders WHERE id = ? AND user_id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, orderId);
      stmt.setLong(2, userId);

      var rs = stmt.executeQuery();
      return rs.next();
    }
  }

  @Override
  public boolean isOwnerOfRating(long userId, long ratingId) throws Exception {
    final var query = "SELECT * FROM ratings WHERE id = ? AND user_id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, ratingId);
      stmt.setLong(2, userId);

      var rs = stmt.executeQuery();
      return rs.next();
    }
  }

  @Override
  public boolean isOwnerOfCart(long userId, long cartId) throws Exception {
    final var query = "SELECT * FROM carts WHERE id = ? AND user_id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, cartId);
      stmt.setLong(2, userId);

      var rs = stmt.executeQuery();
      return rs.next();
    }
  }
}
