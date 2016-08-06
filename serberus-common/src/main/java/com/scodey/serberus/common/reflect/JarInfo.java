package com.scodey.serberus.common.reflect;

import com.scodey.serberus.common.exceptions.SerberusException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;

public class JarInfo {
  private Iterable<Class> classes;
  public JarInfo(File file) {
    this.classes = captureClassFqns(file, loadJar(file));
  }

  private ClassLoader loadJar(File file) {
    try {
      return URLClassLoader.newInstance(
        new URL[] { file.toURL() },
        getClass().getClassLoader()
      );
    } catch (MalformedURLException ex) {
      throw new SerberusException(ex, "Invalid location");
    }
  }

  private List<Class> captureClassFqns(File file, ClassLoader loader) {
    try {
      Process process = Runtime.getRuntime().exec(String.format("jar -tf %s", file.getAbsolutePath()));
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      List<Class> classFqns = new ArrayList<>();

      String value;
      while ((value = reader.readLine()) != null) {
        try {
          if (value.endsWith(".class")) {
            String className = parseClassName(value);
            classFqns.add(loader.loadClass(className));
          }
        } catch (Exception ex) {
          /* NO OP */
        }
      }


      return classFqns;
    } catch (IOException ex) {
      throw new SerberusException(ex, "Unable to parse jar info for", file);
    }
  }

  private String parseClassName(String original) {
    StringBuilder builder = new StringBuilder();
    for (int ix = 0; ix < original.length(); ix++) {
      if (original.charAt(ix) == '/') {
        builder.append('.');
      } else {
        builder.append(original.charAt(ix));
      }
    }

    return builder.toString().replace(".class", "");
  }

  public static JarInfo inFile(String path) {
    return new JarInfo(new File(path));
  }

  public Iterable<Class> classes() { return this.classes; }
}
