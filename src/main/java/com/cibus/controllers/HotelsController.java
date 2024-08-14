package com.cibus.controllers;

import java.util.List;
import java.util.Map;

import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.opensymphony.xwork2.ModelDriven;

import com.cibus.common.dtos.HotelDto;
import com.cibus.common.models.HotelModel;
import com.cibus.common.models.UserModel;
import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.repository.HotelRepository;

// Need to validate
public class HotelsController implements ModelDriven<Object>, SessionAware {
  private HotelDto dto = new HotelDto();
  private List<HotelModel> hotels;
  private HotelModel hotel;

  private Map<String, Object> session;

  private Long ownerId;
  private Long id;

  public void setId(long id) {
    this.id = id;
  }

  public long getId() {
    return id;
  }

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }

  public long getOwnerId() {
    return ownerId;
  }

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  @Override
  public Object getModel() {
    if(hotels != null) {
      return hotels;
    }

    if(hotel != null) {
      return hotel;
    }

    return dto;
  }

  /**
   * GET hotels?ownerId=1
   *  -- gives hotels with owner id 1
   * 
   * GET hotels
   *  -- gives all hotels
   * @return
   * @throws Exception
   */
  public HttpHeaders index() throws Exception {
    final var repo = new HotelRepository(Database.getConnection());

    if (ownerId != null) {
      hotels = repo.getHotelsByOwnerId(getOwnerId());
    } else {
      hotels = repo.getAllHotels();
    }

    return new DefaultHttpHeaders("index");
  }

  /**
   * POST hotels
   */
  public HttpHeaders create() throws Exception {
    final var repo = new HotelRepository(Database.getConnection());
    repo.addHotel(dto);
    return new DefaultHttpHeaders("create");
  }

  /**
   * PUT hotels/id
   */
  public HttpHeaders update() throws Exception {
    final var repo = new HotelRepository(Database.getConnection());
    var model = new HotelModel(getId());
    model.setOwnerId(dto.getOwnerId());
    model.setCity(dto.getCity());
    model.setName(dto.getCity());
    repo.updateHotel(model);
    return new DefaultHttpHeaders("create");
  }

  public HttpHeaders destroy() throws Exception {
    final var repo = new HotelRepository(Database.getConnection());
    repo.deleteHotel(getId());
    return new DefaultHttpHeaders("destroy");
  }
}
