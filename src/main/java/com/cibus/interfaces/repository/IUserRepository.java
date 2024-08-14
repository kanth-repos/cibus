package com.cibus.interfaces.repository;

import com.cibus.common.dtos.UserDto;
import com.cibus.common.models.UserModel;

public interface IUserRepository {
  public UserModel getUser(String email) throws Exception;
  UserModel getUser(long userId) throws Exception;
  UserModel addUser(UserDto user) throws Exception;
  void updateUser(UserModel user) throws Exception;
  void deleteUser(UserModel user) throws Exception;
  void deleteUser(long id) throws Exception;
}
