package com.cibus.repository;

import java.sql.Connection;

import com.cibus.common.dtos.UserDto;
import com.cibus.common.models.UserModel;
import com.cibus.exceptions.UserNotFoundException;
import com.cibus.interfaces.repository.IUserRepository;

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
  public void addUser(UserDto user) throws Exception {
    final var query = "INSERT INTO users (type, name, mobile, email, password) VALUES (?, ?, ?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setString(1, user.getType());
      stmt.setString(2, user.getName());
      stmt.setString(3, user.getMobile());
      stmt.setString(4, user.getEmail());
      stmt.setString(5, user.getPassword());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteUser(UserModel user) throws Exception {
    final var query = "DELETE FROM users WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, user.getId());
      stmt.executeUpdate();
    }
  }
}
