package com.scodey.serberus.common.tls.handshake;

import com.scodey.serberus.common.exceptions.SerberusException;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import static com.scodey.serberus.common.tools.Safe.unchecked;

public enum ContentType {
  CHANGE_CIPHER_SPEC(20),
  ALERT(21),
  HANDSHAKE(22),
  APPLICATION_DATA(23);

  private final int code;
  ContentType(int code) {
    this.code = code;
  }

  public int code() {
    return this.code;
  }

  public static ContentType fromStream(InputStream stream) {
    int code = unchecked(stream::read);
    switch (code) {
      case 20: return CHANGE_CIPHER_SPEC;
      case 21: return ALERT;
      case 22: return HANDSHAKE;
      case 23: return APPLICATION_DATA;
      default:
        throw new SerberusException("Invalid content type: %s", code);
    }
  }
}
