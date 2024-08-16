package com.cibus.actions;

import com.opensymphony.xwork2.ActionSupport;

import java.util.Map;

import org.apache.struts2.action.SessionAware;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.exceptions.UserNotFoundException;
import com.cibus.models.UserModel;
import com.cibus.repository.UserRepository;

public class SignInController extends ActionSupport implements SessionAware {
  @Override
  public void validate() {
    if (getPassword() == null || getPassword().isEmpty()) {
      addFieldError("user.password", "Password is required");
    }

    if (getEmail() == null || getEmail().isEmpty()) {
      addFieldError("user.email", "Email is required");
    }
  }

  private Map<String, Object> session;
  private String email;
  private String password;

  public void setEmail(String email) {
    this.email = email;
  }

  public String getEmail() {
    return email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPassword() {
    return password;
  }

  @Override
  public String execute() throws Exception {
    try (var connection = Database.getConnection()) {
      final var repo = new UserRepository(connection);
      final UserModel user;

      try {
        user = repo.getUser(email);
      } catch (UserNotFoundException e) {
        return INPUT;
      }

      if (!user.verifyPassword(password)) {
        return INPUT;
      }

      session.put(Constants.USER_SESSION, user);

      return SUCCESS;
    }
  }

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }
}
