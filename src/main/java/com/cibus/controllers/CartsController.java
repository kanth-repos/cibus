package com.cibus.controllers;

import java.util.List;
import java.util.Map;

import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;
import com.opensymphony.xwork2.ModelDriven;

import com.cibus.common.dtos.CartDto;
import com.cibus.common.models.CartModel;
import com.cibus.common.models.UserModel;
import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.repository.CartRepository;
import com.cibus.repository.HotelRepository;

// Need to validate
public class CartsController implements ModelDriven<Object>, SessionAware {
  private CartDto dto = new CartDto();
  private List<CartModel> carts;
  private CartModel cart;

  private Map<String, Object> session;

  private Long foodid;

  public void setFoodId(long id) {
    this.foodid = id;
  }

  public long getFoodId() {
    return foodid;
  }

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  @Override
  public Object getModel() {
    if(carts != null) {
      return carts;
    }

    if(cart != null) {
      return cart;
    }

    return dto;
  }

  /**
   * 
   */
  public HttpHeaders index() throws Exception {
    final var repo = new CartRepository(Database.getConnection());

    return new DefaultHttpHeaders("index");
  }

  /**
   *
   */
  public HttpHeaders create() throws Exception {
    final var repo = new CartRepository(Database.getConnection());
    repo.addCart(dto);
    return new DefaultHttpHeaders("create");
  }

  /**
   *
   */
  public HttpHeaders update() throws Exception {
    final var repo = new CartRepository(Database.getConnection());
    return new DefaultHttpHeaders("update");
  }

  public HttpHeaders destroy() throws Exception {
    final var repo = new CartRepository(Database.getConnection());
    return new DefaultHttpHeaders("destroy");
  }
}
