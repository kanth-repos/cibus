package com.cibus.actions;

import com.cibus.constants.Constants;
import com.cibus.models.UserModel;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;

import java.util.Map;

public class DashboardAction extends ActionSupport implements SessionAware {
  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  private Map<String, Object> session;

  public String execute() throws Exception {
    return ((UserModel)session.get(Constants.USER_SESSION)).getType();
  }
}
