package com.cibus.interfaces.repository;

import com.cibus.dtos.UserDto;
import com.cibus.models.UserModel;

public interface IUserRepository {
  public UserModel getUser(String email) throws Exception;
  UserModel getUser(long userId) throws Exception;
  UserModel addUser(UserDto user) throws Exception;
  void updateUser(UserModel user) throws Exception;
  void deleteUser(UserModel user) throws Exception;
  void deleteUser(long id) throws Exception;

  boolean isOwnerOfRating(long userId, long ratingId) throws Exception;
  boolean isOwnerOfCart(long userId, long cartId) throws Exception;
  boolean isOwnerOfFood(long userId, long foodId) throws Exception;
  boolean isOwnerOfHotel(long userId, long hotelId) throws Exception;
  boolean isOwnerOfOrder(long userId, long orderId) throws Exception;
}
