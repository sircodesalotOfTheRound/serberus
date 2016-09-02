package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;

public class CssContent extends HttpContent {
  public CssContent(String content) {
    super(MimeTypeHeaderInfo.CSS, content);
  }
}
