package com.cibus.intercept;

public class ConsoleLogInterceptor extends AbstractLogInterceptor {
  public void log(String line) {
    System.out.println(line);
  }
}
