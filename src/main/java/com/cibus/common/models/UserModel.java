package com.cibus.common.models;

import java.io.Serializable;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserModel implements Serializable {
  public boolean verifyPassword(String pass) {
    return BCrypt.verifyer().verify(pass.toCharArray(), this.getPassword()).verified;
  }

  // Java Bean for User
  public void setPassword(String password) { 
    this.password = password;
  }

  public void setType(String type) { 
    this.type = type; 
  }

  public void setName(String name) { 
    this.name = name; 
  }

  public void setMobile(String mobile) { 
    this.mobile = mobile; 
  }

  public void setEmail(String email) { 
    this.email = email; 
  }

  public String getPassword() { 
    return password; 
  }

  public long getId() { 
    return id; 
  }

  public String getType() { 
    return type; 
  }

  public String getName() { 
    return name; 
  }

  public String getMobile() { 
    return mobile; 
  }

  public String getEmail() { 
    return email; 
  }

  public UserModel(long id) {
    this.id = id;
  }

  // Model attributes for User
  private long id;
  private String type;
  private String name;
  private String mobile;
  private String email;
  private String password;
};
