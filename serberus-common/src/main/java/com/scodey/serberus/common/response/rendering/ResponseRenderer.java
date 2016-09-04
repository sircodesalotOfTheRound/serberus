package com.scodey.serberus.common.response.rendering;

import com.scodey.serberus.common.tools.functional.Functional;

import java.io.IOException;
import java.io.OutputStream;

public class ResponseRenderer implements AutoCloseable {
  private static final byte NEWLINE = '\n';

  private final OutputStream stream;
  public ResponseRenderer(OutputStream stream) {
    this.stream = stream;
  }

  public void write(byte data) throws IOException {
    stream.write(data);
  }

  public void write(byte[] data) throws IOException {
    stream.write(data);
  }

  public void println() throws IOException {
    write(NEWLINE);
    stream.flush();
  }

  public void println(String format, Object... args) throws IOException {
    String data = String.format(format, args);
    for (int ix = 0; ix < data.length(); ix++) {
      stream.write(data.charAt(ix));
    }

    write(NEWLINE);
    stream.flush();
  }

  @Override
  public void close() throws Exception {
    stream.close();
  }

  public void render(ResponseRenderable renderable) throws IOException {
    renderable.render(this);
  }

  public void renderAll(Iterable<ResponseRenderable> renderables) throws IOException {
    for (ResponseRenderable renderable : renderables) {
      render(renderable);
    }
  }
}
