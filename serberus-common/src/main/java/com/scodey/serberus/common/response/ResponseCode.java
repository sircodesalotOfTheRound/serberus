package com.scodey.serberus.common.response;

import com.scodey.serberus.common.response.rendering.ResponseRenderable;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public enum ResponseCode implements ResponseRenderable {
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
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println("HTTP/1.1 %s %s", responseCode, message);
  }

  // Todo: Make this accept a renderer.
  @Override
  public String toString() {
    return String.format("HTTP/1.1 %s %s", responseCode, message);
  }
}
