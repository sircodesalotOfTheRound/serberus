package com.scodey.serberus.common.tools.socket;

import java.io.InputStream;
import java.net.Socket;

import static com.scodey.serberus.common.tools.Safe.unchecked;

public class SocketInputStreamReader {
  private final InputStream stream;
  public SocketInputStreamReader(InputStream stream) {
    this.stream = stream;
  }

  public boolean available() {
    return unchecked(() -> stream.available() != 0);
  }

  public String readLine() {
    StringBuilder builder = new StringBuilder();
    while (available()) {
      char value = (char)(int)unchecked(stream::read);
      if (value == '\r') continue;
      if (value == '\n') {
        return builder.toString();
      } else {
        builder.append(value);
      }
    }

    return builder.toString();
  }
}
