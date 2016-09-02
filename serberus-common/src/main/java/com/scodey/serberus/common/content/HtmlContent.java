package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;

public class HtmlContent extends HttpContent {
  public HtmlContent(String content) {
    super(MimeTypeHeaderInfo.HTML, content);
  }
}
