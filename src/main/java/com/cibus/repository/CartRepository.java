package com.cibus.repository;

import com.cibus.common.dtos.CartDto;
import com.cibus.common.models.CartModel;
import com.cibus.exceptions.DatabaseException;
import com.cibus.interfaces.ICartRepository;

import java.sql.Connection;
import java.util.ArrayList;

public class CartRepository implements ICartRepository {
  public CartRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addCart(CartDto cart) {
    final String query = "INSERT INTO carts (user_id, food_id, quantity) VALUES (?, ?, ?)";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, cart.getUserId());
      stmt.setLong(2, cart.getFoodId());
      stmt.setInt(3, cart.getQuantity());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public ArrayList<CartModel> getCartsByUserId(long id) {
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
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void deleteCartsByUserId(long userId) {
    final String query = "DELETE FROM carts WHERE user_id = " + userId;
    try(var stmt = connection.createStatement()) {
      stmt.executeUpdate(query);
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void updateCart(CartModel cart) {
    final String query = "UPDATE carts SET quantity = ? WHERE user_id = ? AND food_id = ?";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, cart.getQuantity());
      stmt.setLong(2, cart.getUserId());
      stmt.setLong(3, cart.getFoodId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }

  @Override
  public void deleteCart(CartModel cart) {
    final String query = "DELETE FROM carts where user_id = ? AND food_id = ?";
    try(var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, cart.getUserId());
      stmt.setLong(2, cart.getFoodId());
      stmt.executeUpdate();
    } catch (Exception e) {
      throw new DatabaseException(e);
    }
  }
}
