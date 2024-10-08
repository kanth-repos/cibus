package com.cibus.exceptions;

import java.sql.SQLException;

public class UserNotFoundException extends SQLException {
  public UserNotFoundException(String message) { super(message); }

  public UserNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public UserNotFoundException(Throwable cause) { super(cause); }
}
