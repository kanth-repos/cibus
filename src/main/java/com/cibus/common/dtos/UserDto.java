package com.cibus.common.dtos;

import at.favre.lib.crypto.bcrypt.BCrypt;

public class UserDto {
  // Java Bean for User
  public void setPassword(String password) { 
    this.password = BCrypt.withDefaults().hashToString(12, password.toCharArray()); 
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

  // Model attributes for User
  private String type;
  private String name;
  private String mobile;
  private String email;
  private String password;
};
