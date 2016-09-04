package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.header.response.ResponseHeader;
import com.scodey.serberus.common.response.rendering.ResponseRenderable;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public abstract class HttpContent<T> implements ResponseRenderable {
  private final MimeTypeHeaderInfo mimeType;
  protected final T content;
  protected final Set<ResponseHeader> headers;

  public HttpContent(MimeTypeHeaderInfo mimeType, T content, ResponseHeader... headers) {
    this.mimeType = mimeType;
    this.content = content;
    this.headers = stream(headers).collect(Collectors.toSet());

    addHeader(mimeType);
  }

  protected void addHeader(ResponseHeader header) {
    this.headers.add(header);
  }

  public MimeTypeHeaderInfo mimeType() {
    return this.mimeType;
  }

  public T content() { return this.content; }
  public Iterable<ResponseHeader> headers() { return this.headers; }

  public abstract void render(ResponseRenderer renderer) throws IOException;

  @Override
  public String toString() {
    return content.toString();
  }
}
