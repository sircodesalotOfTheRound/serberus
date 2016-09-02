package com.scodey.serberus.common.header.response;

public class MimeTypeHeaderInfo extends ResponseHeader {
  public static MimeTypeHeaderInfo PLAIN = new MimeTypeHeaderInfo("text/plain");
  public static MimeTypeHeaderInfo HTML = new MimeTypeHeaderInfo("text/html");
  public static MimeTypeHeaderInfo CSS = new MimeTypeHeaderInfo("text/css");

  private final String type;
  private MimeTypeHeaderInfo(String type) {
    super("Content-type:");

    this.type = type;
  }

  public String type() { return type; }

  public String toString() {
    return String.format("%s %s", name(), type());
  }
}
