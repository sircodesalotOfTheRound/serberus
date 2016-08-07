package com.scodey.serberus.common.tools;

public class Safe {
  public String safeToString(Object object) {
    if (object == null) {
      return "null";
    } else {
      return object.toString();
    }
  }
}
