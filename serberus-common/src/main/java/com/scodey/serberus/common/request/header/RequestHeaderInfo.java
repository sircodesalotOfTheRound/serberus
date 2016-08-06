package com.scodey.serberus.common.request.header;

import com.scodey.serberus.common.tools.parsing.Lexer;

import java.util.HashMap;
import java.util.Map;

public abstract class RequestHeaderInfo {
  private final String name;

  public RequestHeaderInfo(String name) {
    this.name = name;
  }

  public String name() { return this.name; }

  @FunctionalInterface
  public interface MakeRequestHeaderCallback { RequestHeaderInfo make(Lexer lexer); }
  private static final Map<String, MakeRequestHeaderCallback> callbacks = new HashMap<String, MakeRequestHeaderCallback>() {{
    put("Host:", HostNameHeaderInfo::new);
  }};


  public static boolean supportsHeaderInfoFor(String text) {
    Lexer lexer = new Lexer(text);
    String headerKey = lexer.readWhileNot(Character::isWhitespace);

    return callbacks.containsKey(headerKey);
  }

  public static RequestHeaderInfo headerInfoFor(String text) {
    Lexer lexer = new Lexer(text);
    String headerKey = lexer.readWhileNot(Character::isWhitespace);
    lexer.readAndAdvance(Character::isWhitespace);

    return callbacks
      .get(headerKey)
      .make(lexer);
  }
}
