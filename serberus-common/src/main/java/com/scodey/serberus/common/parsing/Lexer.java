package com.scodey.serberus.common.parsing;

import com.scodey.serberus.common.validation.Validation;

import java.util.function.Predicate;

public class Lexer {
  private final String text;
  private int index;

  public Lexer(String text) {
    this.text = text;
  }

  public boolean isEof() { return index == text.length(); }
  public boolean notEof() { return !isEof(); }

  public char current() {
    Validation.check(notEof(), "Read past end of file");
    return text.charAt(index);
  }

  public boolean currentIs(char letter) {
    return notEof() && current() == letter;
  }

  public boolean currentIs(Predicate<Character> predicate) {
    return notEof() && predicate.test(current());
  }

  public void advance() {
    Validation.check(notEof(), "Advance past end of file");
    index++;
  }

  public char readAndAdvance() {
    char value = current();
    advance();
    return value;
  }

  public char readAndAdvance(char letter) {
    Validation.check(currentIs(letter), "Expected %s", letter);
    return readAndAdvance();
  }

  public char readAndAdvance(Predicate<Character> predicate) {
    Validation.check(predicate.test(current()), "Current letter did not match predicate");
    return readAndAdvance();
  }

  public String readAndAdvance(String text) {
    for (int index = 0; index < text.length(); index++) {
      readAndAdvance(text.charAt(index));
    }

    return text;
  }

  public String readWhileNot(Predicate<Character> predicate) {
    StringBuilder builder = new StringBuilder();
    while (!currentIs(predicate)) {
      builder.append(readAndAdvance());
    }

    return builder.toString();
  }

  public String readToEnd() {
    StringBuilder builder = new StringBuilder();
    while (notEof()) {
      builder.append(readAndAdvance());
    }

    return builder.toString();
  }

  public String readWhile(Predicate<Character> predicate) {
    StringBuilder builder = new StringBuilder();
    while (currentIs(predicate)) {
      builder.append(readAndAdvance());
    }

    return builder.toString();
  }
}
