package com.cibus.interfaces;

import com.cibus.common.models.UserModel;

public interface IUserRepository {
  UserModel getUser(long userId);
  void addUser(UserModel user);
  void updateUser(UserModel user);
  void deleteUser(UserModel user);
}
