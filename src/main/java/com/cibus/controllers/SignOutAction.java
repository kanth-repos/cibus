package com.cibus.controllers;

import com.cibus.annotations.RequiresAuth;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;

import java.util.Map;

@RequiresAuth
public class SignOutAction extends ActionSupport implements SessionAware {
  @Override
  public void withSession(Map<String, Object> session) {
    this.session = (SessionMap) session;
  }

  private SessionMap session;

  @Override
  public String execute() throws Exception {
    session.invalidate();
    return SUCCESS;
  }
}
