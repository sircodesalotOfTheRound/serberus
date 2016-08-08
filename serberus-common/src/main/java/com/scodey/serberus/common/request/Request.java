package com.scodey.serberus.common.request;

import com.scodey.serberus.common.exceptions.SerberusException;
import com.scodey.serberus.common.request.header.RequestHeaderInfo;
import com.scodey.serberus.common.tools.InvertedIndex;
import com.scodey.serberus.common.tools.parsing.Lexer;
import com.scodey.serberus.common.tools.socket.SocketInputStreamReader;
import com.scodey.serberus.common.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Request {
  private final RequestLine requestLine;
  private final InvertedIndex<Class, RequestHeaderInfo> headers;

  public Request(InputStream inputStream) {
    SocketInputStreamReader reader = new SocketInputStreamReader(inputStream);
    this.requestLine = new RequestLine(new Lexer(reader.readLine()));
    this.headers = captureHeaders(reader);
  }

  private BufferedReader makeReader(InputStream inputStream) {
    return new BufferedReader(new InputStreamReader(inputStream));
  }

  private String readLine(BufferedReader reader) {
    try {
      return reader.readLine();
    } catch (IOException ex) {
      throw new SerberusException(ex, "Unable to read line");
    }
  }

  private InvertedIndex<Class, RequestHeaderInfo> captureHeaders(SocketInputStreamReader reader) {
    InvertedIndex<Class, RequestHeaderInfo> index = new InvertedIndex<>(RequestHeaderInfo::getClass);

    while (reader.available()) {
      String value = reader.readLine();
      if (value.isEmpty()) break;

      System.err.println(value);
      if (RequestHeaderInfo.supportsHeaderInfoFor(value)) {
        index.add(RequestHeaderInfo.headerInfoFor(value));
      }
    }

    System.err.println();
    System.err.flush();

    return index;
  }

  public RequestMethod method() { return requestLine.method(); }
  public String uri() { return requestLine.uri(); }
  public HttpVersion httpVersion() { return requestLine.httpVersion(); }

  public boolean hasHeaderOfType(Class<? extends RequestHeaderInfo> type) {
    return headers.containsFor(type);
  }

  public <T extends RequestHeaderInfo> T headerFor(Class<T> type) {
    Validation.check(hasHeaderOfType(type), "Request does not contain a header for: %s", type.getName());
    return (T)headers.get(type);
  }
}
