package com.cibus.intercept;

import com.cibus.logger.AbstractLogger;

public class LogInterceptor extends AbstractLogger {
  public void log(String line) {
    System.out.println(line);
  }
}
