package com.scodey.serberus.common.tls.handshake;

import java.io.InputStream;

public class ClientHello {
  private final ContentType contentType;

  public ClientHello(InputStream stream) {
    this.contentType = parseContentType(stream);
  }

  private ContentType parseContentType(InputStream stream) {
    return ContentType.fromStream(stream);
  }
}
