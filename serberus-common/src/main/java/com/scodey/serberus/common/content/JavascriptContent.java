package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.ContentLengthHeader;
import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.header.response.ResponseHeader;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;

public class JavascriptContent extends HttpContent<String> {
  public JavascriptContent(String content) {
    super(MimeTypeHeaderInfo.JAVASCRIPT, content, new ContentLengthHeader(content.length()));
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println(super.content);
  }
}
