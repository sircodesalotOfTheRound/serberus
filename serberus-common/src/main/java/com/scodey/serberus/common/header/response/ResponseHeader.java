package com.scodey.serberus.common.header.response;

import com.scodey.serberus.common.header.HttpHeader;
import com.scodey.serberus.common.response.rendering.ResponseRenderable;

public abstract class ResponseHeader extends HttpHeader implements ResponseRenderable {
  protected ResponseHeader(String name) {
    super(name);
  }

  @Override
  public int hashCode() {
    return this.getClass().hashCode();
  }

  @Override
  public boolean equals(Object other) {
    return (other != null) && (getClass() == other.getClass());
  }
}
