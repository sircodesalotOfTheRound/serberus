package com.scodey.serberus.common.header.response;

import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class SetCookieHeader extends ResponseHeader {
  private final String data;
  public SetCookieHeader(String data) {
    super("Set-Cookie");

    this.data = data;
  }

  public String data() {
    return this.data;
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    super.renderKeyValue(renderer, data);
  }
}
