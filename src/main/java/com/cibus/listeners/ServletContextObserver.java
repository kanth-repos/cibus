package com.cibus.listeners;

import com.cibus.constants.Constants;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.sql.DataSource;
import javax.annotation.Resource;

public class ServletContextObserver implements ServletContextListener {
  @Resource(name = "jdbc/database")
  private DataSource dataSource;

  @Override
  public void contextInitialized(ServletContextEvent sce) {
    sce.getServletContext().setAttribute(Constants.DATA_SOURCE, dataSource);
  }

  @Override
  public void contextDestroyed(ServletContextEvent sce) {
    // Do nothing
  }
}
