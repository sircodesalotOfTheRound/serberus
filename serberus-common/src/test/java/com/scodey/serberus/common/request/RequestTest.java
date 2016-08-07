package com.scodey.serberus.common.request;

import com.scodey.serberus.common.request.header.HostNameHeaderInfo;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RequestTest {
  private static final String NEW_LINE = "\n";

  @Test
  public void testSimpleRequest() throws IOException {
    InputStream inputStream = new InputStream() {
      int index = 0;
      String requestText = new StringBuilder()
        .append("GET /stuff HTTP/1.1").append(NEW_LINE)
        .append("Host: localhost").append(NEW_LINE)
        .append("\n")
        .toString();

      @Override
      public int read() throws IOException {
        if (index < requestText.length()) {
          return requestText.charAt(index++);
        } else {
          return -1;
        }
      }
    };

    Request request = new Request(inputStream);

    assertEquals(RequestMethod.GET, request.method());
    assertEquals("/stuff", request.uri());
    assertEquals(HttpVersion.HTTP_1_1, request.httpVersion());

    assertTrue(request.hasHeaderOfType(HostNameHeaderInfo.class));
    assertEquals("localhost", request.headerFor(HostNameHeaderInfo.class).hostname());
  }
}