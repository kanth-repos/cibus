package com.cibus.logger;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.Instant;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public abstract class AbstractLogger extends AbstractInterceptor  {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    var req = invocation.getInvocationContext().getServletRequest();
    var format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    var start = Instant.now();
    var action = invocation.invoke();
    var end = Instant.now();
    var taken = Duration.between(start, end).toMillis();

    this.log(
      format.format(start) + ":" + req.getMethod() + " " + 
      req.getPathInfo() + " " + taken + "ms"
    );

    return action;
  }

  abstract public void log(String line);
}
