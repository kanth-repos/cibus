package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.common.dtos.OrderDto;
import com.cibus.common.models.OrderModel;

public interface IOrderRepository {
  ArrayList<OrderModel> getOrdersByUserId(long userId) throws Exception;
  OrderModel addOrder(OrderDto order) throws Exception;
  void updateOrder(OrderModel order) throws Exception;
  void deleteOrder(OrderModel order) throws Exception;
  void deleteOrder(long id) throws Exception;
}
