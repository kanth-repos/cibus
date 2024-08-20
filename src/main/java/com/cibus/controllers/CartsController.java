package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.CartDto;
import com.cibus.models.CartModel;
import com.cibus.models.UserModel;
import com.cibus.repository.CartRepository;
import com.cibus.repository.UserRepository;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class CartsController implements ModelDriven<Object>, SessionAware {
  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  private Map<String, Object> session;
  private CartDto dto = new CartDto();
  private CartModel cart;
  private List<CartModel> carts;

  private Long userId;
  private Long id;

  public void setUserId(long userId) { this.userId = userId; }

  public Long getUserId() { return userId; }

  public void setId(long id) { this.id = id; }

  public Long getId() { return id; }

  @Override
  public Object getModel() {
    if (carts != null) {
      return carts;
    } else if (cart != null) {
      return cart;
    } else {
      return dto;
    }
  }

  /**
   * GET /carts?userId=userId
   */
  public HttpHeaders index() throws Exception {
    try (var connection = Database.getConnection()) {
      final var cartRepo = new CartRepository(connection);
      final var user = (UserModel)session.get(Constants.USER_SESSION);
  
      if (getUserId() == null) {
        return new DefaultHttpHeaders("index").withStatus(400);
      }
  
      if (user.getId() != getUserId()) {
        return new DefaultHttpHeaders("index").withStatus(403);
      }
  
      carts = cartRepo.getCartsByUserId(getUserId());
  
      return new DefaultHttpHeaders("index");
    }
  }

  /**
   * GET /carts/id
   */
  public HttpHeaders show() throws Exception {
    try (var connection = Database.getConnection()) {
      final var cartRepo = new CartRepository(connection);
      cart = cartRepo.getCart(getId());

      if (cart == null) {
        return new DefaultHttpHeaders("show").withStatus(404);
      }
    
      return new DefaultHttpHeaders("show");
    }
  }

  /**
   * POST /carts
   */
  public HttpHeaders create() throws Exception {
    try (var connection = Database.getConnection()) {
      final var cartRepo = new CartRepository(connection);
      final var user = (UserModel)session.get(Constants.USER_SESSION);
  
      if (user.getId() != dto.getUserId()) {
        return new DefaultHttpHeaders("create").withStatus(401);
      }

      cart = cartRepo.getCart(dto.getUserId(), dto.getFoodId());

      if(cart != null) {
        cart.setQuantity(cart.getQuantity() + dto.getQuantity());
        cartRepo.updateCart(cart); 
      } else {
        cart = cartRepo.addCart(dto);
      }
  
      return new DefaultHttpHeaders("create");
    }
  }

  /**
   * PUT /carts/id
   */
  public HttpHeaders update() throws Exception {
    try (var connection = Database.getConnection()) {
      final var cartRepo = new CartRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel)session.get(Constants.USER_SESSION);
  
      if (getId() == null) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }
  
      if (user.getId() != dto.getUserId()) {
        return new DefaultHttpHeaders("update").withStatus(401);
      }
  
      if (!userRepo.isOwnerOfCart(user.getId(), id)) {
        return new DefaultHttpHeaders("update").withStatus(401);
      }
  
      cart = cartRepo.getCart(getId());
      if(dto.getQuantity()!=null) cart.setQuantity(dto.getQuantity());
      if(dto.getUserId()!=null) cart.setUserId(dto.getUserId());
      if(dto.getFoodId()!=null) cart.setFoodId(dto.getFoodId());
  
      cartRepo.updateCart(cart);
  
      return new DefaultHttpHeaders("update");
    }
  }

  /**
   * DELETE /carts/id
   */
  public HttpHeaders destroy() throws Exception {
    try (var connection = Database.getConnection()) {
      final var cartRepo = new CartRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel)session.get(Constants.USER_SESSION);
  
      if (getId() == null) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }
  
      if (!userRepo.isOwnerOfCart(user.getId(), getId())) {
        return new DefaultHttpHeaders("destroy").withStatus(401);
      }
  
      cartRepo.deleteCart(getId());
  
      return new DefaultHttpHeaders("destroy");
    }
  }
}
