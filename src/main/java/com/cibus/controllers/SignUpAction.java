package com.cibus.controllers;

import com.opensymphony.xwork2.ActionSupport;
import java.util.Arrays;
import com.cibus.common.dtos.UserDto;
import com.cibus.database.Database;
import com.cibus.repository.UserRepository;

public class SignUpAction extends ActionSupport {
  @Override
  public void validate() {
    final var allowedTypes = Arrays.asList("owner", "user");

    if (user == null) {
      addActionError("User is required");
      return;
    }

    if(user.getPassword() == null || user.getPassword().isEmpty()) {
      addFieldError("user.password", "Password is required");
    }

    if(user.getName() == null || user.getName().isEmpty()) {
      addFieldError("user.username", "Username is required");
    }

    if(!allowedTypes.contains(user.getType())) {
      addFieldError("user.type", "Type is required");
    }

    if(user.getEmail() == null || user.getEmail().isEmpty()) {
      addFieldError("user.email", "Email is required");
    }
  }

  private UserDto user;

  public void setUser(UserDto user) {
    this.user = user;
  }

  public UserDto getUser() {
    return user;
  }

  @Override
  public String execute() throws Exception {
    final var connection = Database.getConnection();
    final var repo = new UserRepository(connection);
    repo.addUser(user);
    return SUCCESS;
  }
}
