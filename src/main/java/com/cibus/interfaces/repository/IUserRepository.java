package com.cibus.interfaces.repository;

import com.cibus.common.dtos.UserDto;
import com.cibus.common.models.UserModel;

public interface IUserRepository {
  UserModel getUser(long userId) throws Exception;
  void addUser(UserDto user) throws Exception;
  void updateUser(UserModel user) throws Exception;
  void deleteUser(UserModel user) throws Exception;
}
