package com.cibus.intercept;

import org.apache.struts2.dispatcher.SessionMap;

import com.cibus.common.models.UserModel;
import com.cibus.constants.Constants;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class OwnerInterceptor extends AbstractInterceptor {
  @Override
  public String intercept(ActionInvocation invocation) throws Exception {
    var session = (SessionMap) invocation.getInvocationContext().getSession();

    if (!session.containsKey(Constants.USER_SESSION)) {
      return "signin";
    }

    var user = (UserModel) session.get(Constants.USER_SESSION);

    if (user.getType().equalsIgnoreCase("owner")) {
      return invocation.invoke();
    }

    session.invalidate();
    return "signin";
  }
}
