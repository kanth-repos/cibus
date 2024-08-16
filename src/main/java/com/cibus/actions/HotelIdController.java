package com.cibus.actions;

import com.opensymphony.xwork2.ActionSupport;

public class HotelIdController extends ActionSupport {
  private Long hotelId;

  public void setHotelId(Long hotelId) {
    this.hotelId = hotelId;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public String execute() throws Exception {
    return SUCCESS;
  }
}
