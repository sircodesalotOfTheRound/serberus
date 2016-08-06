package com.scodey.serberus.common.validation;

import com.scodey.serberus.common.exceptions.ValidationException;

public class Validation {
  public static void check(boolean test, String format, Object... args) {
    if (!test) throw new ValidationException(format, args);
  }
}
