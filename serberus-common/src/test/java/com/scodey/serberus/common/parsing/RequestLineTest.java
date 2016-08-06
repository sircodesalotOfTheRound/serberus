package com.scodey.serberus.common.parsing;

import com.scodey.serberus.common.request.HttpVersion;
import org.junit.Test;

import static com.scodey.serberus.common.request.RequestMethod.GET;
import static org.junit.Assert.assertEquals;

public class RequestLineTest {

  @Test
  public void testRequestLine() {
    RequestLine requestLine = fromString("GET / HTTP/1.1");

    assertEquals(GET, requestLine.method());
    assertEquals("/", requestLine.uri());
    assertEquals(HttpVersion.HTTP_1_1, requestLine.httpVersion());
  }

  private RequestLine fromString(String text) {
    return new RequestLine(new Lexer(text));
  }
}