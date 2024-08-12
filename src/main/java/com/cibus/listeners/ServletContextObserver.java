package com.cibus.listeners;

import com.cibus.constants.Constants;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class ServletContextObserver implements ServletContextListener {
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    try {
      var envCtx = (Context) (new InitialContext()).lookup("java:comp/env");
      DataSource dataSource = (DataSource) envCtx.lookup("jdbc/database");
      sce.getServletContext().setAttribute(Constants.DATA_SOURCE, dataSource);
    } catch (NamingException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // Do nothing
  }
}
