package com.cibus.controllers;

import com.opensymphony.xwork2.ActionSupport;

import com.cibus.database.Database;
import com.cibus.repository.UserRepository;

public class SignInAction extends ActionSupport {
  @Override
  public void validate() {
    if(getPassword() == null || getPassword().isEmpty()) {
      addFieldError("user.password", "Password is required");
    }

    if(getEmail() == null || getEmail().isEmpty()) {
      addFieldError("user.email", "Email is required");
    }
  }

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
    return SUCCESS;
  }
}
