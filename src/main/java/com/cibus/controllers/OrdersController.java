package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.OrderDto;
import com.cibus.models.OrderModel;
import com.cibus.models.UserModel;
import com.cibus.repository.OrderRepository;
import com.cibus.repository.UserRepository;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class OrdersController implements ModelDriven<Object>, SessionAware {
  private OrderDto dto = new OrderDto();
  private List<OrderModel> orders;
  private OrderModel order;

  private Map<String, Object> session;

  private Long hotelId;
  private Long userId;
  private Long id;

  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }

  public Long getHotelId() {
    return hotelId;
  }

  public void setUserId(long userId) {
    this.userId = userId;
  }

  public Long getUserId() {
    return userId;
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
    if (orders != null) {
      return orders;
    } else if (order != null) {
      return order;
    } else {
      return dto;
    }
  }

  /**
   * GET /orders?userId=userId?hotelId=hotelId
   */
  public HttpHeaders index() throws Exception {
    try (var connection = Database.getConnection()) {
      final var orderRepo = new OrderRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getHotelId() == null && getUserId() == null ||
          getHotelId() != null && getUserId() != null) {
        return new DefaultHttpHeaders("index").withStatus(400);
      }

      if (getHotelId() != null && !userRepo.isOwnerOfHotel(user.getId(), getHotelId())) {
        return new DefaultHttpHeaders("index").withStatus(401);
      }

      if (getHotelId() != null) {
        orders = orderRepo.getOrdersByHotelId(getHotelId());
      }

      if (getUserId() != null && user.getId() != getUserId()) {
        return new DefaultHttpHeaders("index").withStatus(401);
      }

      if (getUserId() != null) {
        orders = orderRepo.getOrdersByUserId(getUserId());
      }

      return new DefaultHttpHeaders("index");
    }
  }

  /**
   * GET /orders/id
   */
  public HttpHeaders show() throws Exception {
    try (var connection = Database.getConnection()) {
      final var orderRepo = new OrderRepository(connection);
      order = orderRepo.getOrder(getId());

      if (order == null) {
        return new DefaultHttpHeaders("show").withStatus(404);
      }

      return new DefaultHttpHeaders("show");
    }
  }

  /**
   * POST /orders
   */
  public HttpHeaders create() throws Exception {
    try (var connection = Database.getConnection()) {
      final var orderRepo = new OrderRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (user.getId() != dto.getUserId()) {
        return new DefaultHttpHeaders("create").withStatus(401);
      }

      dto.setUserId(user.getId());
      orderRepo.addOrder(dto);
      return new DefaultHttpHeaders("create");
    }
  }

  /**
   * DELETE /orders/{id}
   */
  public HttpHeaders destroy() throws Exception {
    try (var connection = Database.getConnection()) {
      final var orderRepo = new OrderRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("destroy").withStatus(400);
      }

      if (!userRepo.isOwnerOfOrder(user.getId(), getId())) {
        return new DefaultHttpHeaders("destroy").withStatus(401);
      }

      orderRepo.deleteOrder(getId());

      return new DefaultHttpHeaders("destroy");
    }
  }
}
