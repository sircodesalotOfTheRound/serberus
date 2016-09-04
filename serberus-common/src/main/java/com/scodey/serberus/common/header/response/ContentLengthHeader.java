package com.scodey.serberus.common.header.response;

import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class ContentLengthHeader extends ResponseHeader {
  private final int length;

  public ContentLengthHeader(int length) {
    super("Content-Length");

    this.length = length;
  }

  public int length() { return this.length; }

  @Override
  public String toString() {
    return String.format("%s: %s", name(), length());
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println("%s: %s", name(), length());
  }
}
