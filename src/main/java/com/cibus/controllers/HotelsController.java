package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.HotelDto;
import com.cibus.models.HotelModel;
import com.cibus.models.UserModel;
import com.cibus.repository.HotelRepository;
import com.cibus.repository.UserRepository;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

// Need to validate
public class HotelsController implements ModelDriven<Object>, SessionAware {
  private HotelDto dto = new HotelDto();
  private List<HotelModel> hotels;
  private HotelModel hotel;

  private Map<String, Object> session;

  private Long ownerId;
  private Long id;

  public void setOwnerId(long ownerId) {
    this.ownerId = ownerId;
  }

  public Long getOwnerId() {
    return ownerId;
  }

  public void setId(long id) {
    this.id = id;
  }

  public Long getId() {
    return id;
  }

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  @Override
  public Object getModel() {
    if (hotels != null) {
      return hotels;
    } else if (hotel != null) {
      return hotel;
    } else {
      return dto;
    }
  }

  /**
   * GET /hotels?ownerId=ownerId (optional)
   */
  public HttpHeaders index() throws Exception {
    try (var connection = Database.getConnection()) {
      final var user = (UserModel) session.get(Constants.USER_SESSION);
      final var repo = new HotelRepository(connection);

      if (getOwnerId() != null && user.getId() != getOwnerId()) {
        return new DefaultHttpHeaders("index").withStatus(403);
      }

      if (getOwnerId() != null) {
        hotels = repo.getHotelsByOwnerId(getOwnerId());
      } else {
        hotels = repo.getAllHotels();
      }

      return new DefaultHttpHeaders("index");
    }
  }

  /**
   * POST /hotels
   */
  public HttpHeaders create() throws Exception {
    try (var connection = Database.getConnection()) {
      final var user = (UserModel) session.get(Constants.USER_SESSION);
      final var repo = new HotelRepository(connection);

      if (user.getType().equalsIgnoreCase("user")) {
        return new DefaultHttpHeaders("create").withStatus(403);
      }

      if (user.getId() != dto.getOwnerId()) {
        return new DefaultHttpHeaders("create").withStatus(403);
      }

      hotel = repo.addHotel(dto);

      return new DefaultHttpHeaders("create");
    }
  }

  /**
   * PUT /hotels/id
   */
  public HttpHeaders update() throws Exception {
    try (var connection = Database.getConnection()) {
      final var hotelRepo = new HotelRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }

      if (!userRepo.isOwnerOfHotel(user.getId(), getId())) {
        return new DefaultHttpHeaders("create").withStatus(403);
      }

      hotel = new HotelModel(getId());

      if (dto.getOwnerId() != null)
        hotel.setOwnerId(dto.getOwnerId());
      if (dto.getCity() != null)
        hotel.setCity(dto.getCity());
      if (dto.getCity() != null)
        hotel.setName(dto.getCity());

      hotelRepo.updateHotel(hotel);

      return new DefaultHttpHeaders("create");
    }
  }

  /**
   * DELETE /hotels/id
   */
  public HttpHeaders destroy() throws Exception {
    try (var connection = Database.getConnection()) {
      final var hotelRepo = new HotelRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("destroy").withStatus(400);
      }

      if (!userRepo.isOwnerOfHotel(user.getId(), getId())) {
        return new DefaultHttpHeaders("create").withStatus(403);
      }

      hotelRepo.deleteHotel(getId());
      return new DefaultHttpHeaders("destroy");
    }
  }
}
