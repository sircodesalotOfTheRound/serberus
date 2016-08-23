package com.scodey.serberus.common.tools.socket;

import java.io.InputStream;

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
    int tries = 0;

    while (tries++ < 3) {
      try {
        while (available()) {
          char value = (char) (int) unchecked(stream::read);
          if (value == '\r') continue;
          if (value == '\n') {
            return builder.toString();
          } else {
            builder.append(value);
          }
        }
        Thread.sleep(200 * tries);
      } catch (InterruptedException ex) {
        throw new RuntimeException(ex);
      }
    }

    return builder.toString();
  }
}
