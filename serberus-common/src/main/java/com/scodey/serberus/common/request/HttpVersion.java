package com.scodey.serberus.common.request;

import com.scodey.serberus.common.validation.Validation;

import java.util.HashMap;
import java.util.Map;

public enum HttpVersion {
  HTTP_1_1;

  private static final Map<String, HttpVersion> STRING_TO_VERSION = new HashMap<String, HttpVersion>() {{
    put("HTTP/1.1", HTTP_1_1);
  }};

  public static HttpVersion fromString(String text) {
    Validation.check(STRING_TO_VERSION.containsKey(text), "Invalid Http Version");
    return STRING_TO_VERSION.get(text);
  }
}
