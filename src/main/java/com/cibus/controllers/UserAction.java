package com.cibus.controllers;

import com.cibus.annotations.RequiresAuth;
import com.cibus.common.models.HotelModel;
import com.cibus.database.Database;
import com.cibus.repository.HotelRepository;
import com.opensymphony.xwork2.ActionSupport;

import java.util.ArrayList;

@RequiresAuth
public class UserAction extends ActionSupport {
  private ArrayList<HotelModel> hotels;

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
    this.setHotels(repo.getAllHotels());
    return SUCCESS;
  }
}
