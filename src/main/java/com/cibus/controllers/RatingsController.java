package com.cibus.controllers;

import com.cibus.constants.Constants;
import com.cibus.database.Database;
import com.cibus.dtos.RatingDto;
import com.cibus.exceptions.RowNotFoundException;
import com.cibus.models.RatingModel;
import com.cibus.models.UserModel;
import com.cibus.repository.RatingRepository;
import com.cibus.repository.UserRepository;
import com.cibus.utility.Utility;
import com.opensymphony.xwork2.ModelDriven;
import java.util.List;
import java.util.Map;
import org.apache.struts2.action.SessionAware;
import org.apache.struts2.rest.DefaultHttpHeaders;
import org.apache.struts2.rest.HttpHeaders;

public class RatingsController implements ModelDriven<Object>, SessionAware {
  private RatingDto dto = new RatingDto();
  private List<RatingModel> ratings;
  private RatingModel rating;

  private Map<String, Object> session;

  private Long foodId;
  private Long userId;
  private Long id;

  public void setFoodId(long foodId) {
    this.foodId = foodId;
  }

  public Long getFoodId() {
    return foodId;
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
    if (ratings != null) {
      return ratings;
    } else if (rating != null) {
      return rating;
    } else {
      return dto;
    }
  }

  /**
   * GET /ratings?foodId=foodId?userId=userId
   */
  public HttpHeaders index() throws Exception {
    try (var connection = Database.getConnection()) {
      final var ratingRepo = new RatingRepository(connection);

      if (getFoodId() == null && getUserId() == null) {
        return new DefaultHttpHeaders("index").withStatus(400);
      }

      if (getFoodId() != null && getUserId() != null) {
        try {
          rating = ratingRepo.getRating(getUserId(), getFoodId());
        } catch(RowNotFoundException e) {
          return new DefaultHttpHeaders("index").withStatus(400);
        }
      }

      if (getFoodId() != null) {
        ratings = ratingRepo.getRatingsByFoodId(getFoodId());
      }

      if (getUserId() != null) {
        ratings = ratingRepo.getRatingsByUserId(getUserId());
      }
      
      return new DefaultHttpHeaders("index");
    }
  }

  /**
   * GET /ratings/id
   */
  public HttpHeaders show() throws Exception {
    try (var connection = Database.getConnection()) {
      final var ratingsRepo = new RatingRepository(connection);
      rating = ratingsRepo.getRating(getId());

      if (rating == null) {
        return new DefaultHttpHeaders("show").withStatus(404);
      }

      return new DefaultHttpHeaders("show");
    }
  }

  /**
   * POST /ratings
   */
  public HttpHeaders create() throws Exception {
    try (var connection = Database.getConnection()) {
      final var ratingRepo = new RatingRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if(!Utility.validateCreateGroupDto(dto).isEmpty()) {
        return new DefaultHttpHeaders("create").withStatus(400);
      }

      if (user.getId() != dto.getUserId()) {
        return new DefaultHttpHeaders("create").withStatus(401);
      }

      try {
        ratingRepo.getRating(dto.getUserId(), dto.getFoodId());
      } catch (RowNotFoundException e) {
        ratingRepo.addRating(dto);
        return new DefaultHttpHeaders("create");
      }

      return new DefaultHttpHeaders("create").withStatus(409);
    }
  }

  /**
   * PUT /ratings/{id}
   */
  public HttpHeaders update() throws Exception {
    try (var connection = Database.getConnection()) {
      final var ratingRepo = new RatingRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if(!Utility.validateUpdateGroupDto(dto).isEmpty()) {
        return new DefaultHttpHeaders("create").withStatus(400);
      }

      if (user.getId() != dto.getUserId()) {
        return new DefaultHttpHeaders("update").withStatus(401);
      }

      if (getId() == null) {
        return new DefaultHttpHeaders("update").withStatus(400);
      }

      rating = ratingRepo.getRating(getId());
      
      rating.setUserId(dto.getUserId());
      rating.setFoodId(dto.getFoodId());
      
      if(dto.getRating()!=null) {
        rating.setRating(dto.getRating());
      }

      if(dto.getMessage()!=null) {
        rating.setMessage(dto.getMessage());
      }

      ratingRepo.updateRating(rating);

      return new DefaultHttpHeaders("update");
    }
  }

  /**
   * DELETE /ratings/{id}
   */
  public HttpHeaders destroy() throws Exception {
    try (var connection = Database.getConnection()) {
      final var ratingRepo = new RatingRepository(connection);
      final var userRepo = new UserRepository(connection);
      final var user = (UserModel) session.get(Constants.USER_SESSION);

      if (getId() == null) {
        return new DefaultHttpHeaders("destroy").withStatus(400);
      }

      if (!userRepo.isOwnerOfRating(user.getId(), getId())) {
        return new DefaultHttpHeaders("destroy").withStatus(401);
      }

      ratingRepo.deleteRating(getId());

      return new DefaultHttpHeaders("destroy");
    }
  }
}
