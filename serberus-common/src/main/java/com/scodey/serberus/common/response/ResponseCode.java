package com.scodey.serberus.common.response;

public enum ResponseCode {
  OK(200, "OK", "Request was processed successfully"),
  NOT_FOUND(404, "Not Found", "No such endpoint found");

  int responseCode;
  String message;
  String description;

  ResponseCode(int responseCode, String message, String description) {
    this.responseCode = responseCode;
    this.message = message;
    this.description = description;
  }

  public int responseCode() { return this.responseCode; }
  public String message() { return this.message; }
  public String description() { return this.description; }

  @Override
  public String toString() {
    return String.format("%s %s", responseCode, message);
  }
}
