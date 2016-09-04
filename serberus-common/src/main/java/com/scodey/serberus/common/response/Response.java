package com.scodey.serberus.common.response;

import com.scodey.serberus.common.content.HttpContent;
import com.scodey.serberus.common.content.PlainContent;
import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderable;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;
import com.scodey.serberus.common.tools.functional.Functional;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.scodey.serberus.common.header.response.MimeTypeHeaderInfo.PLAIN;
import static com.scodey.serberus.common.tools.Strings.NEW_LINE;
import static com.scodey.serberus.common.tools.functional.Functional.cast;
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

    // Todo: make this accept only HttpContent.
    public void writeContent(Object content) {
      if (content instanceof HttpContent) {
        this.content = (HttpContent)content;
      } else {
        this.content = new PlainContent(content.toString());
      }
    }

    public Response build() { return new Response(this); }
  }

  private final ResponseCode responseCode;
  private final HttpContent<?> content;

  public Response(Builder builder) {
    this.responseCode = builder.responseCode;
    this.content = builder.content;
  }

  public ResponseCode responseCode() { return this.responseCode; }
  public HttpContent content() { return this.content; }

  // Todo: Refactor this to use a real renderer.
  public void send(OutputStream stream) throws IOException {
    try (ResponseRenderer renderer = new ResponseRenderer(stream)) {
      renderer.render(responseCode);
      renderer.renderAll(cast(content.headers(), ResponseRenderable.class));
      renderer.println();
      renderer.render(content);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
