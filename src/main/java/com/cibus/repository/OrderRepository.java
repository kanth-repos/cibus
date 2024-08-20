package com.cibus.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import com.cibus.dtos.OrderDto;
import com.cibus.exceptions.RowNotFoundException;
import com.cibus.interfaces.repository.IOrderRepository;
import com.cibus.models.OrderModel;

public class OrderRepository implements IOrderRepository {
  public OrderRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public ArrayList<OrderModel> getOrdersByHotelId(long hotelId) throws Exception {
    final var query = "SELECT * FROM orders JOIN foods ON orders.food_id = foods.id WHERE foods.hotel_id = " + hotelId;
    try (var stmt = connection.createStatement()) {
      var orders = new ArrayList<OrderModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var orderId = rs.getLong(1);
        var userId = rs.getLong(2);
        var foodId = rs.getLong(3);
        var order = new OrderModel(orderId);
        order.setUserId(userId);
        order.setFoodId(foodId);
        order.setQuantity(rs.getInt(4));
        orders.add(order);
      }

      return orders;
    }
  }

  @Override
  public OrderModel addOrder(OrderDto order) throws Exception {
    final var query = "INSERT INTO orders (user_id, food_id, quantity) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
      stmt.setLong(1, order.getUserId());
      stmt.setLong(2, order.getFoodId());
      stmt.setInt(3, order.getQuantity());
      stmt.executeUpdate();

      OrderModel model = null;

      try (var generatedKeys = stmt.getGeneratedKeys()) {
        if (generatedKeys.next()) {
          model = new OrderModel(generatedKeys.getLong(1));
        } else {
          throw new SQLException("Can't get Primary Key");
        }
      }

      model.setQuantity(order.getQuantity());
      model.setUserId(order.getUserId());
      model.setFoodId(order.getFoodId());
      return model;
    }
  }

  @Override
  public ArrayList<OrderModel> getOrdersByUserId(long id) throws Exception {
    final var query = "SELECT * FROM orders where user_id = " + id;

    try (var stmt = connection.createStatement()) {
      var orders = new ArrayList<OrderModel>();
      var rs = stmt.executeQuery(query);

      while(rs.next()) {
        var orderId = rs.getLong(1);
        var userId = rs.getLong(2);
        var foodId = rs.getLong(3);
        var order = new OrderModel(orderId);
        order.setUserId(userId);
        order.setFoodId(foodId);
        order.setQuantity(rs.getInt(4));
        orders.add(order);
      }

      return orders;
    }
  }

  @Override
  public void updateOrder(OrderModel order) throws Exception {
    final var query = "UPDATE orders SET quantity = ?, user_id = ?, food_id = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, order.getQuantity());
      stmt.setLong(2, order.getUserId());
      stmt.setLong(3, order.getFoodId());
      stmt.setLong(4, order.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteOrder(OrderModel order) throws Exception {
    this.deleteOrder(order.getId());
  }

  @Override
  public void deleteOrder(long id) throws Exception {
    final var query = "DELETE FROM orders WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, id);
      stmt.executeUpdate();
    }
  }

  @Override
  public OrderModel getOrder(long id) throws Exception {
    final var query = "SELECT * FROM orders where user_id = " + id;

    try (var stmt = connection.createStatement()) {
      var rs = stmt.executeQuery(query);

      if(!rs.next()) {
        throw new RowNotFoundException(query);
      }

      var orderId = rs.getLong(1);
      var userId = rs.getLong(2);
      var foodId = rs.getLong(3);
      var order = new OrderModel(orderId);
      order.setUserId(userId);
      order.setFoodId(foodId);
      order.setQuantity(rs.getInt(4));
      return order;
    }
  }
}
