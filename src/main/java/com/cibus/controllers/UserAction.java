package com.cibus.controllers;

import com.cibus.annotations.RequiresAuth;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;

import java.util.Map;

@RequiresAuth
public class UserAction extends ActionSupport implements SessionAware {
  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  private Map<String, Object> session;

  @Override
  public String execute() throws Exception {
    return SUCCESS;
  }
}
