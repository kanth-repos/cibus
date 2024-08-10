package com.cibus.interfaces;

import java.util.ArrayList;

import com.cibus.common.dtos.CartDto;
import com.cibus.common.models.CartModel;

public interface ICartRepository {
  ArrayList<CartModel> getCartsByUserId(long userId);
  void deleteCartsByUserId(long userId);

  void addCart(CartDto cart);
  void updateCart(CartModel cart);
  void deleteCart(CartModel cart);
}
