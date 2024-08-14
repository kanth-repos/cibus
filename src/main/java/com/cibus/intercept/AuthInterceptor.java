package com.cibus.intercept;

import org.apache.struts2.dispatcher.SessionMap;

import com.cibus.constants.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    var session = (SessionMap)invocation.getInvocationContext().getSession();

    if(!session.containsKey(Constants.USER_SESSION)) {
      return "signin";
    }

    return invocation.invoke();
  }
}
