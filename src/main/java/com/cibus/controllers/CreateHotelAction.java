package com.cibus.controllers;

import java.util.Map;

import org.apache.struts2.action.SessionAware;
import com.cibus.annotations.RequiresAuth;
import com.cibus.annotations.UserAsOwner;
import com.cibus.common.dtos.HotelDto;
import com.cibus.common.models.UserModel;
import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.repository.HotelRepository;
import com.opensymphony.xwork2.ActionSupport;

@RequiresAuth
@UserAsOwner
public class CreateHotelAction extends ActionSupport implements SessionAware {
  private Map<String, Object> session;
  private HotelDto hotel;

  @Override
  public void validate() {
    if(hotel.getName() == null || hotel.getName().isEmpty()) {
      addFieldError("hotel.name", "Name should not be empty");
    }

    if(hotel.getCity() == null || hotel.getCity().isEmpty()) {
      addFieldError("hotel.city", "City should not be empty");
    }

    var user = ((UserModel)session.get(Constants.USER_SESSION));

    if(user.getId() != hotel.getOwnerId()) {
      addFieldError("hotel.ownerId", "Owner id not matching");
    }
  }

  @Override
  public String execute() throws Exception {
    final var connection = Database.getConnection();
    final var repo = new HotelRepository(connection);
    repo.addHotel(hotel);
    return SUCCESS;
  }

  public HotelDto getHotel() {
    return hotel;
  }

  public void setHotel(HotelDto hotel) {
    this.hotel = hotel;
  }

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }
}
