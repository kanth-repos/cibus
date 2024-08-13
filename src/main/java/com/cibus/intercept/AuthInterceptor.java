package com.cibus.intercept;

import com.cibus.annotations.RequiresAuth;
import com.cibus.constants.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    var session = invocation.getInvocationContext().getSession();
    var action = invocation.getAction().getClass();

    if(!action.isAnnotationPresent(RequiresAuth.class)) {
      return invocation.invoke();
    }

    if(!session.containsKey(Constants.USER_SESSION)) {
      return "signin";
    }

    return invocation.invoke();
  }
}
