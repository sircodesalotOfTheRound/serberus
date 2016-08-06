package com.scodey.serberus.common.request.header;

import com.scodey.serberus.common.tools.parsing.Lexer;

public class HostNameHeaderInfo extends RequestHeaderInfo {
  private final String host;

  public HostNameHeaderInfo(Lexer lexer) {
    super("Host");
    this.host = lexHost(lexer);
  }

  private String lexHost(Lexer lexer)  {
    return lexer.readToEnd();
  }

  public String hostname() { return this.host; }
}
