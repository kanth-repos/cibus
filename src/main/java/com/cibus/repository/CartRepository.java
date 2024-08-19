package com.cibus.repository;

import com.cibus.interfaces.repository.ICartRepository;
import com.cibus.models.CartModel;
import com.cibus.dtos.CartDto;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class CartRepository implements ICartRepository {
  public CartRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public CartModel addCart(CartDto cart) throws Exception {
    final String query = "INSERT INTO carts (user_id, food_id, quantity) VALUES (?, ?, ?)";
    try(var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setLong(1, cart.getUserId());
      stmt.setLong(2, cart.getFoodId());
      stmt.setInt(3, cart.getQuantity());
      stmt.executeUpdate();

      CartModel model = null;

      try (var generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          model = new CartModel(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Can't get Primary Key");
        }
      }

      model.setQuantity(cart.getQuantity());
      model.setUserId(cart.getUserId());
      model.setFoodId(cart.getFoodId());

      return model;
    }
  }

  @Override
  public ArrayList<CartModel> getCartsByUserId(long id) throws Exception {
    final String query = "SELECT * FROM carts WHERE user_id = " + id;
    try(var stmt = connection.createStatement()) {
      var carts = new ArrayList<CartModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var cart = new CartModel(rs.getLong(1));
        cart.setUserId(rs.getLong(2));
        cart.setFoodId(rs.getLong(3));
        cart.setQuantity(rs.getInt(4));
        carts.add(cart);
      }

      return carts;
    }
  }

  @Override
  public void deleteCartsByUserId(long userId) throws Exception {
    final String query = "DELETE FROM carts WHERE user_id = " + userId;
    try(var stmt = connection.createStatement()) {
      stmt.executeUpdate(query);
    }
  }

  @Override
  public void updateCart(CartModel cart) throws Exception {
    final String query = "UPDATE carts SET quantity = ?, user_id = ?, food_id = ? WHERE id = ?";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, cart.getQuantity());
      stmt.setLong(2, cart.getUserId());
      stmt.setLong(3, cart.getFoodId());
      stmt.setLong(4, cart.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteCart(CartModel cart) throws Exception {
    this.deleteCart(cart.getId());
  }

  @Override
  public void deleteCart(long id) throws Exception {
    final String query = "DELETE FROM carts WHERE id = " + id;
    try(var stmt = connection.createStatement()) {
      stmt.executeUpdate(query);
    }
  }

  @Override
  public CartModel getCart(long userId, long foodId) throws Exception {
    final String findQuery = "SELECT * from carts where user_id = ? and food_id = ?";
    try(var stmt = connection.prepareStatement(findQuery)) {
      stmt.setLong(1, userId);
      stmt.setLong(2, foodId);

      var rs = stmt.executeQuery();

      if(!rs.next()) {
        return null;
      }

      var model = new CartModel(rs.getInt(1));
      model.setUserId(rs.getLong(2));
      model.setFoodId(rs.getLong(3));
      model.setQuantity(rs.getInt(4));
      return model;
    }

  }
}
