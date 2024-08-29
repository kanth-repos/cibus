package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.UserDto;
import com.cibus.models.UserModel;
import com.cibus.repository.UserRepository;
import com.cibus.utility.Utility;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import java.util.Set;

import javax.validation.ConstraintViolation;

import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class UsersController implements ModelDriven<Object>, SessionAware {
  private Set<ConstraintViolation<UserDto>> violations;

  private Map<String, Object> session;
  private UserDto dto = new UserDto();
  private UserModel user;

  private Long id;

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
    } else if (user != null) {
      return user;
    } else {
      return dto;
    }
  }

  /**
   * GET /users
   */
  public HttpHeaders index() throws Exception {
    user = (UserModel) session.get(Constants.USER_SESSION);
    return new DefaultHttpHeaders("index");
  }

  /**
   * GET /users/id
   */
  public HttpHeaders show() throws Exception {
    try (var connection = Database.getConnection()) {
      final var userRepo = new UserRepository(connection);

      if (getId() == null) {
        return new DefaultHttpHeaders("show").withStatus(400);
      }

      user = userRepo.getUser(getId());

      if (user == null) {
        return new DefaultHttpHeaders("show").withStatus(404);
      }

      return new DefaultHttpHeaders("show");
    }
  }

  /**
   * PUT /users/{id}
   */
  public HttpHeaders update() throws Exception {
    try (var connection = Database.getConnection()) {
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }

      violations = Utility.validateUpdateGroupDto(dto);

      if(!violations.isEmpty()) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }

      if (user.getId() != getId()) {
        return new DefaultHttpHeaders("update").withStatus(401);
      }

      if (dto.getName() != null) {
        user.setName(dto.getName());
      }

      if (dto.getEmail() != null) {
        user.setEmail(dto.getEmail());
      }

      if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
        user.setPassword(dto.getPassword());
      }

      if (dto.getMobile() != null) {
        user.setMobile(dto.getMobile());
      }
      
      if (dto.getType() != null) {
        user.setType(dto.getType());
      }

      userRepo.updateUser(user);

      return new DefaultHttpHeaders("update");
    }
  }

  /**
   * DELETE /users/id
   */
  public HttpHeaders destroy() throws Exception {
    try (var connection = Database.getConnection()) {
      final var userRepo = new UserRepository(connection);
      final var sUser = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("destroy").withStatus(400);
      }

      if (sUser.getId() != getId()) {
        return new DefaultHttpHeaders("destroy").withStatus(401);
      }

      userRepo.deleteUser(getId());

      return new DefaultHttpHeaders("destroy");
    }
  }
}
