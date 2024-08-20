package com.cibus.exceptions;

import java.sql.SQLException;

public class RowNotFoundException extends SQLException {
  public RowNotFoundException(String message) { super(message); }

  public RowNotFoundException(String message, Throwable cause) {
    super(message, cause);
  }

  public RowNotFoundException(Throwable cause) { super(cause); }
}
