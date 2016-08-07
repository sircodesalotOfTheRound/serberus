package com.scodey.serberus.common.request;

import com.scodey.serberus.common.exceptions.SerberusException;
import com.scodey.serberus.common.request.header.RequestHeaderInfo;
import com.scodey.serberus.common.tools.InvertedIndex;
import com.scodey.serberus.common.tools.parsing.Lexer;
import com.scodey.serberus.common.validation.Validation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Request {
  private final RequestLine requestLine;
  private final InvertedIndex<Class, RequestHeaderInfo> headers;

  public Request(InputStream inputStream) {
    BufferedReader reader = makeReader(inputStream);
    this.requestLine = new RequestLine(new Lexer(readLine(reader)));
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

  private InvertedIndex<Class, RequestHeaderInfo> captureHeaders(BufferedReader reader) {
    InvertedIndex<Class, RequestHeaderInfo> index = new InvertedIndex<>(RequestHeaderInfo::getClass);

    String value;
    while (!(value = readLine(reader)).isEmpty()) {
      if (RequestHeaderInfo.supportsHeaderInfoFor(value)) {
        index.add(RequestHeaderInfo.headerInfoFor(value));
      }
    }

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
