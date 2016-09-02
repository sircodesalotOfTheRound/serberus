package com.scodey.serberus.common.header.response;

import com.scodey.serberus.common.header.HttpHeader;

public abstract class ResponseHeader extends HttpHeader {
  protected ResponseHeader(String name) {
    super(name);
  }
}
