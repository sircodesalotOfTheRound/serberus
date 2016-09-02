package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;

public class PlainContent extends HttpContent {
  private static final String EMPTY = "";

  public PlainContent() {
    this("");
  }

  public PlainContent(String content) {
    super(MimeTypeHeaderInfo.PLAIN, content);
  }
}
