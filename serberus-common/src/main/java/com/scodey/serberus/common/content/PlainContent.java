package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class PlainContent extends HttpContent<String> {
  private static final String EMPTY = "";

  public PlainContent() {
    this(EMPTY);
  }

  public PlainContent(String content) {
    super(MimeTypeHeaderInfo.PLAIN, content);
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println(super.content);
  }
}
