package com.scodey.serberus.common.content;

import com.scodey.serberus.common.header.response.MimeTypeHeaderInfo;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;

import java.io.IOException;
import java.util.Map;

public class JsonContent extends HttpContent<String> {

  public JsonContent(Map<String, String> content) {
    super(MimeTypeHeaderInfo.JSON, asString(content));
  }

  private static String asString(Map<String, String> content) {
      return String.format("{%s}",
          String.join(", ",
          content.entrySet().stream()
            .map(entry -> String.format("\"%s\": %s", entry.getKey(), entry.getValue()))
            .map(string -> (CharSequence)string)::iterator
        )
      );
  }

  @Override
  public void render(ResponseRenderer renderer) throws IOException {
    renderer.println(content);
  }

  @Override
  public String toString() {
    return content;
  }
}
