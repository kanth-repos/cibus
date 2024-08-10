package com.cibus.interfaces;

import java.util.ArrayList;

import com.cibus.common.dtos.OrderDto;
import com.cibus.common.models.OrderModel;

public interface IOrderRepository {
  ArrayList<OrderModel> getOrdersByUserId(long userId);
  void addOrder(OrderDto order);
  void updateOrder(OrderModel order);
  void deleteOrder(OrderModel order);
}
