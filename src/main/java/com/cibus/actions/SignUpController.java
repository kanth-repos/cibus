package com.cibus.actions;

import com.opensymphony.xwork2.ActionSupport;
import com.cibus.dtos.UserDto;
import com.cibus.database.Database;
import com.cibus.repository.UserRepository;
import com.cibus.utility.Utility;

import java.util.Arrays;

public class SignUpController extends ActionSupport {
  @Override
  public void validate() {
    final var allowedTypes = Arrays.asList("owner", "user");

    if (user == null) {
      addActionError("User is required");
      return;
    }

    if (user.getPassword() == null || user.getPassword().isEmpty()) {
      addFieldError("user.password", "Password is required");
    }

    if (user.getName() == null || user.getName().isEmpty()) {
      addFieldError("user.username", "Username is required");
    }

    if (!allowedTypes.contains(user.getType().toLowerCase())) {
      addFieldError("user.type", "Type is required");
    }

    if (user.getEmail() == null || user.getEmail().isEmpty()) {
      addFieldError("user.email", "Email is required");
    }

    if(!Utility.validateCreateGroupDto(user).isEmpty()) {
      addActionError("Validation Failed");
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
    try (var connection = Database.getConnection()) {
      final var repo = new UserRepository(connection);
      repo.addUser(user);
      return SUCCESS;
    }
  }
}
