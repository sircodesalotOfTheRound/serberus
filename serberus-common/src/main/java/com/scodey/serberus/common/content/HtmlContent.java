package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class HtmlContent extends HttpContent<String> {
  public HtmlContent(String content) {
    super(MimeTypeHeaderInfo.HTML, content);
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println(super.content);
  }
}
