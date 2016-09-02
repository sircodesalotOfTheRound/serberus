package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;

public abstract class HttpContent {
  private static final String EMPTY = "";
  private final MimeTypeHeaderInfo mimeType;
  private final Object content;

  public HttpContent(MimeTypeHeaderInfo mimeType, Object content) {
    this.mimeType = mimeType;
    this.content = content;
  }

  public MimeTypeHeaderInfo mimeType() {
    return this.mimeType;
  }

  // Todo: Create an actual content renderer.
  @Override
  public String toString() {
    return content.toString();
  }
}
