package com.scodey.serberus.server.run;

import com.scodey.serberus.common.annotations.Endpoint;
import com.scodey.serberus.common.reflect.JarInfo;

import java.lang.reflect.Method;

public class Program {
  public static void main(String[] args) throws Exception {
    JarInfo jarInfo = JarInfo.inFile("/Users/reubenkuhner/Desktop/Dev/serberus/web-service/target/web-service-1.0-SNAPSHOT.jar");
    for (Class type : jarInfo.classes()) {
      for (Method method : type.getDeclaredMethods()) {
        if (method.getAnnotation(Endpoint.class) != null) {
          Object instance = type.newInstance();
          method.invoke(instance);
        }
      }
    }
  }
}
