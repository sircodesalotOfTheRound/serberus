package com.scodey.serberus.common.exceptions;

public class ValidationException extends SerberusException {
  public ValidationException(String format, Object... args) {
    super(format, args);
  }

  public ValidationException(Throwable innerException, String format, Object... args) {
    super(innerException, format, args);
  }
}
