package com.scodey.serberus.common.response;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import static com.scodey.serberus.common.tools.Strings.NEW_LINE;
import static com.scodey.serberus.common.tools.functional.Functional.reduce;

public class Response {
  public static class Builder {
    private final ResponseCode responseCode;
    private List<String> content;

    public Builder(ResponseCode responseCode) {
      this.responseCode = responseCode;
      this.content = new ArrayList<>();
    }

    public void println(String text) {
      content.add(text);
    }

    public Response build() { return new Response(this); }
  }

  private final ResponseCode responseCode;
  private final String content;

  public Response(Builder builder) {
    this.responseCode = builder.responseCode;
    this.content = reduce(new StringBuilder(), builder.content.stream(),
      (stringBuilder, line) -> stringBuilder.append(line).append(NEW_LINE))
        .toString();
  }

  public ResponseCode responseCode() { return this.responseCode; }

  public void send(OutputStream stream) {
    try (PrintWriter writer = new PrintWriter(stream)) {
      writer.println(String.format("HTTP/1.1 %s", responseCode));
      writer.println(String.format("Content-type: text/html"));
      writer.println();
      writer.println(content);
      writer.flush();
    }
  }
}
