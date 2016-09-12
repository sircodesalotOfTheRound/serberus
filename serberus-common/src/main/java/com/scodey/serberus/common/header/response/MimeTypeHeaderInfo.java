package com.scodey.serberus.common.header.response;

import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class MimeTypeHeaderInfo extends ResponseHeader {
  public static MimeTypeHeaderInfo PLAIN = new MimeTypeHeaderInfo("text/plain");
  public static MimeTypeHeaderInfo HTML = new MimeTypeHeaderInfo("text/html");
  public static MimeTypeHeaderInfo CSS = new MimeTypeHeaderInfo("text/css");
  public static MimeTypeHeaderInfo ICON = new MimeTypeHeaderInfo("image/x-icon");
  public static MimeTypeHeaderInfo IMAGE = new MimeTypeHeaderInfo("image/jpg");
  public static MimeTypeHeaderInfo JAVASCRIPT = new MimeTypeHeaderInfo("text/javascript");
  public static MimeTypeHeaderInfo JSON = new MimeTypeHeaderInfo("application/json");

  private final String type;
  private MimeTypeHeaderInfo(String type) {
    super("Content-type");

    this.type = type;
  }

  public String type() { return type; }

  public String toString() {
    return String.format("%s: %s", name(), type());
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println("%s: %s", name(), type());
  }
}
