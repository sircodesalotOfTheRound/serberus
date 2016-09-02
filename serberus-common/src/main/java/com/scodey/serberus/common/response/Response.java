package com.scodey.serberus.common.response;

import com.scodey.serberus.common.content.HttpContent;
import com.scodey.serberus.common.content.PlainContent;
import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.scodey.serberus.common.header.response.MimeTypeHeaderInfo.PLAIN;
import static com.scodey.serberus.common.tools.Strings.NEW_LINE;
import static com.scodey.serberus.common.tools.functional.Functional.reduce;
import static java.lang.String.format;

public class Response {
  public static class Builder {
    private final ResponseCode responseCode;
    private HttpContent content = new PlainContent();

    public Builder(ResponseCode responseCode) {
      this.responseCode = responseCode;
      this.content = new PlainContent();
    }

    public void writeContent(Object content) {
      if (content instanceof HttpContent) {
        this.content = (HttpContent)content;
      } else {
        this.content = new PlainContent(content.toString());
      }
    }

    public Response build() { return new Response(this); }
  }

  private final MimeTypeHeaderInfo mimeType;
  private final ResponseCode responseCode;
  private final HttpContent content;

  public Response(Builder builder) {
    this.responseCode = builder.responseCode;
    this.mimeType = builder.content.mimeType();
    this.content = builder.content;
  }

  public ResponseCode responseCode() { return this.responseCode; }
  public MimeTypeHeaderInfo mimeType() { return this.mimeType; }
  public HttpContent content() { return this.content; }

  // Todo: Refactor this to use a real renderer.
  public void send(OutputStream stream) {
    try (PrintWriter writer = new PrintWriter(stream)) {
      writer.println(responseCode);
      writer.println(mimeType);
      writer.println();
      writer.println(content);
      writer.flush();
    }
  }
}
