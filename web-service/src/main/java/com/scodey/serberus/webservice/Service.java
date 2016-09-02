package com.scodey.serberus.webservice;

import com.scodey.serberus.common.annotations.Controller;
import com.scodey.serberus.common.annotations.Endpoint;
import com.scodey.serberus.common.content.CssContent;
import com.scodey.serberus.common.content.HtmlContent;
import com.scodey.serberus.common.content.HttpContent;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

@Controller
public class Service {
  @Endpoint("/")
  public HttpContent mainSite() throws IOException {
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

  @Endpoint("/style.css")
  public CssContent styleSheet() throws IOException {
     return new CssContent(loadResource("style.css"));
  }

  @Endpoint("/htmlfile")
  public HtmlContent htmlFile() throws IOException {
    return new HtmlContent(loadResource("htmloutput.html"));
  }

  private final String loadResource(String path) throws IOException {
    InputStream resource = Service.class.getClassLoader().getResource(path).openStream();
    try (BufferedReader reader = new BufferedReader(new InputStreamReader(resource))) {
      StringBuilder builder = new StringBuilder();

      String value;
      while ((value = reader.readLine()) != null) {
        builder.append(value);
      }

      return builder.toString();
    }
  }
}
