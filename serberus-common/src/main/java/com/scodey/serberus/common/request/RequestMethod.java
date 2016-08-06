package com.scodey.serberus.common.request;

import com.scodey.serberus.common.validation.Validation;

import java.util.HashMap;
import java.util.Map;

public enum RequestMethod {
  GET, POST, PUT, DELETE, method;

  private static final Map<String, RequestMethod> STRING_TO_METHOD = new HashMap<String, RequestMethod>() {{
    put("GET", GET);
    put("POST", POST);
    put("PUT", PUT);
    put("DELETE", DELETE);
  }};

  public static RequestMethod fromString(String text) {
    Validation.check(STRING_TO_METHOD.containsKey(text), "Invalid request type %s", text);
    return STRING_TO_METHOD.get(text);
  }
}
