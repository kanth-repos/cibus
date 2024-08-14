package com.cibus.controllers;

import com.cibus.annotations.RequiresAuth;
import com.cibus.annotations.UserAsOwner;
import com.cibus.common.models.HotelModel;
import com.cibus.common.models.UserModel;
import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.repository.HotelRepository;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.action.SessionAware;

import java.util.ArrayList;
import java.util.Map;

@RequiresAuth
@UserAsOwner
public class OwnerAction extends ActionSupport implements SessionAware {
  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  private ArrayList<HotelModel> hotels;
  private Map<String, Object> session;

  public void setHotels(ArrayList<HotelModel> hotels) {
    this.hotels = hotels;
  }

  public ArrayList<HotelModel> getHotels() {
    return hotels;
  }

  @Override
  public String execute() throws Exception {
    final var connection = Database.getConnection();
    final var repo = new HotelRepository(connection);
    final var user = (UserModel)session.get(Constants.USER_SESSION);
    this.setHotels(repo.getHotelsByOwnerId(user.getId()));
    return SUCCESS;
  }
}
