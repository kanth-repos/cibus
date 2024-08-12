package com.cibus.repository;

import com.cibus.interfaces.repository.ICartRepository;
import com.cibus.common.dtos.CartDto;
import com.cibus.common.models.CartModel;
import java.sql.Connection;
import java.util.ArrayList;

public class CartRepository implements ICartRepository {
  public CartRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addCart(CartDto cart) throws Exception {
    final String query = "INSERT INTO carts (user_id, food_id, quantity) VALUES (?, ?, ?)";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, cart.getUserId());
      stmt.setLong(2, cart.getFoodId());
      stmt.setInt(3, cart.getQuantity());
      stmt.executeUpdate();
    }
  }

  @Override
  public ArrayList<CartModel> getCartsByUserId(long id) throws Exception {
    final String query = "SELECT * FROM carts WHERE user_id = " + id;
    try(var stmt = connection.createStatement()) {
      var carts = new ArrayList<CartModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var userId = rs.getLong(1);
        var foodId = rs.getLong(2);
        var cart = new CartModel(userId, foodId);
        cart.setQuantity(rs.getInt(3));
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
    final String query = "UPDATE carts SET quantity = ? WHERE user_id = ? AND food_id = ?";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, cart.getQuantity());
      stmt.setLong(2, cart.getUserId());
      stmt.setLong(3, cart.getFoodId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteCart(CartModel cart) throws Exception {
    final String query = "DELETE FROM carts where user_id = ? AND food_id = ?";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, cart.getUserId());
      stmt.setLong(2, cart.getFoodId());
      stmt.executeUpdate();
    }
  }
}
