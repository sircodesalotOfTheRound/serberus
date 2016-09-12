package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class StrictHtmlContent extends HttpContent<String> {
  public StrictHtmlContent(String content) {
    super(MimeTypeHeaderInfo.STRICT_XML, content);
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println(super.content);
  }
}
