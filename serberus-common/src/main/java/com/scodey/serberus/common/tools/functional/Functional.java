package com.scodey.serberus.common.tools.functional;

import com.scodey.serberus.common.content.IconContent;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Functional {
  @FunctionalInterface
  public interface Reducer<T, U> { void reduce(U aggregate, T next); }
  public static <T, U> U reduce(U aggregate, Stream<T> items, Reducer<T, U> reducer) {
     for (T item : (Iterable<T>)items::iterator) {
       reducer.reduce(aggregate, item);
     }

    return aggregate;
  }

  public static <T> Stream<T> stream(Iterable<T> items) {
    return StreamSupport.stream(items.spliterator(), false);
  }

  public static <T, U> Iterable<U> cast(Iterable<T> items, Class<U> toType) {
    List<U> result = new ArrayList<U>();
    for (T item : items) {
      result.add((U)item);
    }

    return result;
  }
}
