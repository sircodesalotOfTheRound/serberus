package com.scodey.serberus.common.tools;

public class Safe {
  public String safeToString(Object object) {
    if (object == null) {
      return "null";
    } else {
      return object.toString();
    }
  }

  @FunctionalInterface
  public interface UncheckedCallback<T> { T callback() throws Throwable; }
  public static <T> T unchecked(UncheckedCallback<T> capture) {
    try {
      return capture.callback();
    } catch (Throwable ex) {
      throw new RuntimeException(ex);
    }
  }
}
