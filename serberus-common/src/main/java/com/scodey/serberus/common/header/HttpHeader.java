package com.scodey.serberus.common.header;

public abstract class HttpHeader {
  private final String name;

  protected HttpHeader(String name) {
    this.name = name;
  }

  public String name() { return this.name; }

}
