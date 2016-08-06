package com.scodey.serberus.common.tools;

import java.util.HashMap;
import java.util.Map;

public class InvertedIndex<T, U> {
  @FunctionalInterface
  public interface KeyOnPropertyCallback<T, U> { T onProperty(U value); }

  private final KeyOnPropertyCallback<T, U> onPropertyCallback;
  private final Map<T, U> index = new HashMap<>();

  public InvertedIndex(KeyOnPropertyCallback<T, U> onProperty) {
    this.onPropertyCallback = onProperty;
  }

  public InvertedIndex<T, U> add(U item) {
    this.index.put(onPropertyCallback.onProperty(item), item);
    return this;
  }

  public boolean containsFor(T key) {
    return index.containsKey(key);
  }

  public U get(T key) {
    return index.get(key);
  }
}
