package com.cibus.intercept;

import org.apache.struts2.dispatcher.SessionMap;

import com.cibus.annotations.RequiresAuth;
import com.cibus.annotations.UserAsOwner;
import com.cibus.common.models.UserModel;
import com.cibus.constants.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthInterceptor extends AbstractInterceptor {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    var session = (SessionMap)invocation.getInvocationContext().getSession();
    var action = invocation.getAction().getClass();
    var user = (UserModel) session.get(Constants.USER_SESSION);

    if(!action.isAnnotationPresent(RequiresAuth.class)) {
      return invocation.invoke();
    }

    if(!session.containsKey(Constants.USER_SESSION)) {
      return "signin";
    }

    if(!action.isAnnotationPresent(UserAsOwner.class)) {
      return invocation.invoke();
    }

    if(user.getType().equalsIgnoreCase("owner")) {
      return invocation.invoke();
    }

    session.invalidate();

    return "signin";
  }
}
