package com.scodey.serberus.common.tools;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Resources {
  public static final int EOF = -1;

  public static String loadResourceToString(ClassLoader classLoader, String path) throws IOException {
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(load(classLoader, path)))) {
      StringBuilder builder = new StringBuilder();

      String value;
      while ((value = reader.readLine()) != null) {
        builder.append(value).append("\n");
      }

      return builder.toString();
    }
  }

  public static byte[] loadResourceAsByteArray(ClassLoader loader, String path) throws IOException {
    List<byte[]> buffers = new ArrayList<>();
    int total = 0;
    try (DataInputStream reader = new DataInputStream(load(loader, path))) {
      int count;
      byte[] buffer = new byte[1024];
      while ((count = reader.read(buffer)) != -1) {
        buffers.add(Arrays.copyOf(buffer, count));
        total += count;
      }
    }

    byte[] result = new byte[total];
    int index = 0;
    for (byte[] buffer : buffers) {
      if ((total - index) >= buffer.length) {
        System.arraycopy(buffer, 0, result, index, buffer.length);
      } else {
        System.arraycopy(buffer, 0, result, index, total - index);
      }

      index += buffer.length;
    }

    return result;
  }

  private static InputStream load(ClassLoader loader, String path) throws IOException {
    return loader.getResource(path).openStream();
  }
}
