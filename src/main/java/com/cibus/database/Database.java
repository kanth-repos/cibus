package com.cibus.database;

import static org.apache.struts2.ServletActionContext.getServletContext;
import static com.cibus.constants.Constants.DATA_SOURCE;
import java.sql.Connection;
import java.sql.SQLException;
import javax.sql.DataSource;

public class Database {
  public static Connection getConnection() throws SQLException {
    final var context = getServletContext();
    final var ds = context.getAttribute(DATA_SOURCE);
    return ((DataSource)ds).getConnection();
  }
}
