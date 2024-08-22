package com.cibus.utility;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;

import com.cibus.annotations.CreateGroup;
import com.cibus.annotations.UpdateGroup;

public class Utility {
  public static <Dto> Set<ConstraintViolation<Dto>> validateCreateGroupDto(Dto dto) {
    var factory = Validation.buildDefaultValidatorFactory();
    var validator = factory.getValidator();
    return validator.validate(dto, CreateGroup.class);
  }

  public static <Dto> Set<ConstraintViolation<Dto>> validateUpdateGroupDto(Dto dto) {
    var factory = Validation.buildDefaultValidatorFactory();
    var validator = factory.getValidator();
    return validator.validate(dto, UpdateGroup.class);
  }
}
