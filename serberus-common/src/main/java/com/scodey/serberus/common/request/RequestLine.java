package com.scodey.serberus.common.request;

import com.scodey.serberus.common.tools.parsing.Lexer;

public class RequestLine {
  private final RequestMethod method;
  private final String uri;
  private final HttpVersion httpVersion;

  public RequestLine(Lexer lexer) {
    this.method = captureMethod(lexer);
    this.uri = captureUri(lexer);
    this.httpVersion = captureVersion(lexer);
  }

  private RequestMethod captureMethod(Lexer lexer) {
    RequestMethod method = RequestMethod.fromString(lexer.readWhileNot(Character::isWhitespace));
    lexer.readAndAdvance(Character::isWhitespace);

    return method;
  }

  private String captureUri(Lexer lexer) {
    String location = lexer.readWhileNot(Character::isWhitespace);
    lexer.readAndAdvance(Character::isWhitespace);

    return location;
  }

  private HttpVersion captureVersion(Lexer lexer) {
    return HttpVersion.fromString(lexer.readToEnd());
  }

  public RequestMethod method() { return this.method; }
  public String uri() { return this.uri; }
  public HttpVersion httpVersion() { return this.httpVersion; }
}
