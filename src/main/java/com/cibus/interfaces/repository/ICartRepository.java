package com.cibus.interfaces.repository;

import com.cibus.dtos.CartDto;
import com.cibus.models.CartModel;

import java.util.ArrayList;

public interface ICartRepository {
  ArrayList<CartModel> getCartsByUserId(long userId) throws Exception;
  void deleteCartsByUserId(long userId) throws Exception;

  CartModel addCart(CartDto cart) throws Exception;
  void updateCart(CartModel cart) throws Exception;
  void deleteCart(CartModel cart) throws Exception;
  void deleteCart(long id) throws Exception;
}
