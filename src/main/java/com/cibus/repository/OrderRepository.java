package com.cibus.repository;

import java.sql.Connection;
import java.util.ArrayList;

import com.cibus.common.dtos.OrderDto;
import com.cibus.common.models.OrderModel;
import com.cibus.interfaces.repository.IOrderRepository;

public class OrderRepository implements IOrderRepository {
  public OrderRepository(Connection connection) {
    this.connection = connection;
  }

  private Connection connection;

  @Override
  public void addOrder(OrderDto order) throws Exception {
    final var query = "INSERT INTO orders (user_id, food_id, quantity) VALUES (?, ?, ?)";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, order.getUserId());
      stmt.setLong(2, order.getFoodId());
      stmt.setInt(3, order.getQuantity());
      stmt.executeUpdate();
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
        var order = new OrderModel(orderId, userId, foodId);
        order.setQuantity(rs.getInt(4));
        orders.add(order);
      }

      return orders;
    }
  }

  @Override
  public void updateOrder(OrderModel order) throws Exception {
    final var query = "UPDATE orders SET quantity = ? WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setInt(1, order.getQuantity());
      stmt.setLong(2, order.getId());
      stmt.executeUpdate();
    }
  }

  @Override
  public void deleteOrder(OrderModel order) throws Exception {
    final var query = "DELETE FROM orders WHERE id = ?";
    try (var stmt = connection.prepareStatement(query)) {
      stmt.setLong(1, order.getId());
      stmt.executeUpdate();
    }
  }
}
