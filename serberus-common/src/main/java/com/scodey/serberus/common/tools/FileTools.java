package com.scodey.serberus.common.tools;

import com.scodey.serberus.common.tools.functional.Functional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileTools {
  public static String readToString(String path) throws IOException {
    return Functional.reduce(new StringBuilder(),
      Files.readAllLines(Paths.get(path)).stream(),
      StringBuilder::append).toString();
  }
}
