package com.scodey.serberus.common.exceptions;

public class SerberusException extends RuntimeException {
  public SerberusException(String format, Object... args) {
    super(String.format(format, args));
  }

  public SerberusException(Throwable innerException, String format, Object... args) {
    super(String.format(format, args), innerException);
  }
}
