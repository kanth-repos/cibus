package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.UserDto;
import com.cibus.models.UserModel;
import com.cibus.repository.UserRepository;
import com.opensymphony.xwork2.ModelDriven;
import java.util.Map;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class UsersController implements ModelDriven<Object>, SessionAware {
  private Map<String, Object> session;
  private UserDto dto = new UserDto();
  private UserModel user;

  private Long id;

  public void setId(long id) { this.id = id; }

  public Long getId() { return id; }

  @Override
  public void withSession(Map<String, Object> session) {
    this.session = session;
  }

  @Override
  public Object getModel() {
    if (user != null) {
      return user;
    } else {
      return dto;
    }
  }

  /**
   * GET /users/id
   */
  public HttpHeaders show() throws Exception {
    final var userRepo = new UserRepository(Database.getConnection());
    final var sUser = (UserModel)session.get(Constants.USER_SESSION);

    if (getId() == null) {
      return new DefaultHttpHeaders("show").withStatus(400);
    }

    if (sUser.getId() != getId()) {
      return new DefaultHttpHeaders("show").withStatus(401);
    }

    user = userRepo.getUser(getId());

    if (user == null) {
      return new DefaultHttpHeaders("show").withStatus(404);
    }

    return new DefaultHttpHeaders("show");
  }

  /**
   * PUT /users/{id}
   */
  public HttpHeaders update() throws Exception {
    final var userRepo = new UserRepository(Database.getConnection());
    final var sUser = (UserModel)session.get(Constants.USER_SESSION);

    if (getId() == null) {
      return new DefaultHttpHeaders("update").withStatus(400);
    }

    if (sUser.getId() != getId()) {
      return new DefaultHttpHeaders("update").withStatus(401);
    }

    user = new UserModel(getId());
    user.setName(dto.getName());
    user.setEmail(dto.getEmail());
    user.setPassword(dto.getPassword());
    user.setMobile(dto.getMobile());
    user.setType(dto.getType());

    userRepo.updateUser(user);

    return new DefaultHttpHeaders("update");
  }

  /**
   * DELETE /users/id
   */
  public HttpHeaders destroy() throws Exception {
    final var userRepo = new UserRepository(Database.getConnection());
    final var sUser = (UserModel)session.get(Constants.USER_SESSION);

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
