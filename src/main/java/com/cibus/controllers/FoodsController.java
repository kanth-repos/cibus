package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.FoodDto;
import com.cibus.models.FoodModel;
import com.cibus.models.UserModel;
import com.cibus.repository.FoodRepository;
import com.cibus.repository.UserRepository;
import com.cibus.utility.Utility;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class FoodsController implements ModelDriven<Object>, SessionAware {
  private Set<ConstraintViolation<FoodDto>> violations;

  private FoodDto dto = new FoodDto();
  private List<FoodModel> foods;
  private FoodModel food;

  private Map<String, Object> session;

  private Long hotelId;
  private Long id;

  public void setHotelId(long hotelId) {
    this.hotelId = hotelId;
  }

  public Long getHotelId() {
    return hotelId;
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
    if (violations != null) {
      return violations;
    } else if (foods != null) {
      return foods;
    } else if (food != null) {
      return food;
    } else {
      return dto;
    }
  }

  /**
   * GET /foods?hotelId=hotelId
   */
  public HttpHeaders index() throws Exception {
    try (var connection = Database.getConnection()) {
      final var repo = new FoodRepository(connection);

      if (getHotelId() == null) {
        return new DefaultHttpHeaders("index").withStatus(400);
      }

      foods = repo.getFoodsByHotelId(getHotelId());

      return new DefaultHttpHeaders("index");
    }
  }

  /**
   * GET /foods/id
   */
  public HttpHeaders show() throws Exception {
    try (var connection = Database.getConnection()) {
      final var foodRepo = new FoodRepository(connection);
      food = foodRepo.getFood(getId());

      if (food == null) {
        return new DefaultHttpHeaders("show").withStatus(404);
      }

      return new DefaultHttpHeaders("show");
    }
  }

  /**
   * POST /foods
   */
  public HttpHeaders create() throws Exception {
    try (var connection = Database.getConnection()) {
      final var FoodsRepo = new FoodRepository(connection);
      final var usersRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      violations = Utility.validateCreateGroupDto(dto);

      if(!violations.isEmpty()) {
        return new DefaultHttpHeaders("create").withStatus(400);
      }

      if (!usersRepo.isOwnerOfHotel(user.getId(), dto.getHotelId())) {
        return new DefaultHttpHeaders("create").withStatus(401);
      }

      food = FoodsRepo.addFood(dto);

      return new DefaultHttpHeaders("create");
    }
  }

  /**
   * PUT /foods/{id}
   */
  public HttpHeaders update() throws Exception {
    try (var connection = Database.getConnection()) {
      final var foodsRepo = new FoodRepository(connection);
      final var usersRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }

      violations = Utility.validateUpdateGroupDto(dto);

      if(!violations.isEmpty()) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }

      if (!usersRepo.isOwnerOfFood(user.getId(), getId())) {
        return new DefaultHttpHeaders("update").withStatus(401);
      }

      food = foodsRepo.getFood(getId());

      food.setHotelId(dto.getHotelId());

      if(dto.getName()!=null) {
        food.setName(dto.getName());
      }

      if(dto.getPrice() !=null) {
        food.setPrice(dto.getPrice());
      }

      foodsRepo.updateFood(food);

      return new DefaultHttpHeaders("update");
    }
  }

  /**
   * DELETE /foods/{id}
   */
  public HttpHeaders destroy() throws Exception {
    try (var connection = Database.getConnection()) {
      final var FoodsRepo = new FoodRepository(connection);
      final var usersRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("destroy").withStatus(400);
      }

      if (!usersRepo.isOwnerOfFood(user.getId(), getId())) {
        return new DefaultHttpHeaders("destroy").withStatus(401);
      }

      FoodsRepo.deleteFood(getId());

      return new DefaultHttpHeaders("destroy");
    }
  }
}
