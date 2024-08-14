package com.cibus.interfaces.repository;

import com.cibus.common.dtos.CartDto;
import com.cibus.common.models.CartModel;
import java.util.ArrayList;

public interface ICartRepository {
  ArrayList<CartModel> getCartsByUserId(long userId) throws Exception;
  void deleteCartsByUserId(long userId) throws Exception;

  CartModel addCart(CartDto cart) throws Exception;
  void updateCart(CartModel cart) throws Exception;
  void deleteCart(CartModel cart) throws Exception;
  void deleteCart(long userId, long foodId) throws Exception;
}
