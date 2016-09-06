package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.ContentLengthHeader;
import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;
import com.scodey.serberus.common.tools.Resources;

import java.io.IOException;

public class IconContent extends HttpContent<byte[]> {
  public IconContent(byte[] data) throws IOException {
    super(MimeTypeHeaderInfo.ICON, data, new ContentLengthHeader(data.length));

    super.addHeader(new ContentLengthHeader(super.content.length));
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.write(super.content);
  }

  @Override
  public String toString() {
    StringBuilder builder = new StringBuilder();
    for (byte data : content) {
      builder.append((char)data);
    }

    return builder.toString();
  }
}
