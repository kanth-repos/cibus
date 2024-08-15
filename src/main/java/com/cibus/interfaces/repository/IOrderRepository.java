package com.cibus.interfaces.repository;

import java.util.ArrayList;

import com.cibus.dtos.OrderDto;
import com.cibus.models.OrderModel;

public interface IOrderRepository {
  ArrayList<OrderModel> getOrdersByHotelId(long hotelId) throws Exception;
  ArrayList<OrderModel> getOrdersByUserId(long userId) throws Exception;
  OrderModel addOrder(OrderDto order) throws Exception;
  void updateOrder(OrderModel order) throws Exception;
  void deleteOrder(OrderModel order) throws Exception;
  void deleteOrder(long id) throws Exception;
}
