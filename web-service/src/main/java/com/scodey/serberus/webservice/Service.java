package com.scodey.serberus.webservice;

import com.scodey.serberus.common.annotations.Controller;
import com.scodey.serberus.common.annotations.Endpoint;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class Service {
  @Endpoint("/")
  public String mainSite() throws IOException {
    // TODO: Reroute
    return htmlFile();
  }

  @Endpoint("/something")
  public Object anEndpoint() {
    return "endpoint executed";
  }

  @Endpoint("/another")
  public String anotherEndpoint() {
    return "bar foo the foo bar man";
  }

  @Endpoint("/htmlfile")
  public String htmlFile() throws IOException {
    InputStream htmlfile = Service.class.getClassLoader().getResource("htmloutput.html").openStream();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(htmlfile))) {
      StringBuilder builder = new StringBuilder();

      String value;
      while ((value = reader.readLine()) != null) {
        builder.append(value);
      }

      return builder.toString();
    }
  }
}
