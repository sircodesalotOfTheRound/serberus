package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.ContentLengthHeader;
import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;
import com.scodey.serberus.common.tools.Resources;

import java.io.IOException;

public class IconContent extends HttpContent<byte[]> {
  public IconContent(ClassLoader loader, String imagePath) throws IOException {
    super(MimeTypeHeaderInfo.ICON, Resources.loadResourceAsByteArray(loader, imagePath));

    super.addHeader(new ContentLengthHeader(super.content.length));
  }

  @Override
  public void render(ResponseRenderer renderer) {

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
