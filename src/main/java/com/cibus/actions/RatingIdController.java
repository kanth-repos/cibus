package com.cibus.actions;

import java.util.Map;

import org.apache.struts2.action.SessionAware;
import org.apache.struts2.dispatcher.SessionMap;

import com.cibus.constants.Constants;
import com.cibus.models.UserModel;
import com.opensymphony.xwork2.ActionSupport;

public class RatingIdController extends ActionSupport implements SessionAware{
  @Override
  public void withSession(Map<String, Object> session) {
    this.session = (SessionMap) session;
  }

  private SessionMap session;
  private Long ratingId;

  public void setRatingId(Long ratingId) {
    this.ratingId = ratingId;
  }

  public Long getRatingId() {
    return ratingId;
  }

  public String execute() throws Exception {
    return ((UserModel) session.get(Constants.USER_SESSION)).getType();
  }
}
