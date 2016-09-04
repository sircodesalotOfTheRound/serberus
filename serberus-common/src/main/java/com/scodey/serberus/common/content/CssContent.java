package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class CssContent extends HttpContent<String> {
  public CssContent(String content) {
    super(MimeTypeHeaderInfo.CSS, content);
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println(super.content);
  }
}
