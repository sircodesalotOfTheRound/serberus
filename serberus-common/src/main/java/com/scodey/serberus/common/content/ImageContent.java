package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.ContentLengthHeader;
import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;
import com.scodey.serberus.common.tools.Resources;

import java.io.IOException;

public class ImageContent extends HttpContent<byte[]> {
  public ImageContent(byte[] data) throws IOException {
    super(MimeTypeHeaderInfo.IMAGE, data, new ContentLengthHeader(data.length));
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.write(super.content);
  }
}
