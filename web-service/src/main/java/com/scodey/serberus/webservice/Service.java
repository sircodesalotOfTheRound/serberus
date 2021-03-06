package com.scodey.serberus.webservice;

import com.scodey.serberus.common.annotations.Controller;
import com.scodey.serberus.common.annotations.Endpoint;
import com.scodey.serberus.common.content.*;
import com.scodey.serberus.common.header.response.ResponseHeader;
import com.scodey.serberus.common.header.response.SetCookieHeader;
import com.scodey.serberus.common.response.rendering.ResponseRenderable;
import com.scodey.serberus.common.response.rendering.ResponseRenderer;
import com.scodey.serberus.common.tools.FileTools;
import com.scodey.serberus.common.tools.Resources;

import java.io.IOException;
import java.util.HashMap;

@Controller
public class Service {
  @Endpoint("/")
  public HttpContent mainSite() throws IOException {
    return new HtmlContent(FileTools.readToString("/Users/rkuhnert/Desktop/page.html"));
  }

  @Endpoint("/json.txt")
  public JsonContent json() {
    return new JsonContent(new HashMap<String, String>() {{
      put("first", "1");
      put("second", "2");
      put("third", "3");
    }});
  }

  @Endpoint("/cookie.js")
  public HttpContent cookieJs() {
    return new JavascriptContent("console.log(document.cookie);", new SetCookieHeader("The cookie value"));
  }

  @Endpoint("/cookie")
  public HttpContent cookie() {
    return new HtmlContent("<script src=\"/cookie.js\"></script><div>Something</div>");
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
     return new CssContent(Resources.loadResourceToString(this.getClass().getClassLoader(), "style.css"));
  }

  @Endpoint("/script.js")
  public JavascriptContent script() throws IOException {
    return new JavascriptContent(Resources.loadResourceToString(this.getClass().getClassLoader(), "script.js"));
  }

  @Endpoint("/favicon.ico")
  public IconContent favicon() throws IOException {
    return new IconContent(Resources.loadResourceAsByteArray(this.getClass().getClassLoader(), "favicon.ico"));
  }

  @Endpoint("/corgi.jpg")
  public ImageContent corgi() throws IOException {
    return new ImageContent(Resources.loadResourceAsByteArray(this.getClass().getClassLoader(), "corgi.jpg"));
  }

  @Endpoint("/htmlfile")
  public HtmlContent htmlFile() throws IOException {
    return new HtmlContent(Resources.loadResourceToString(this.getClass().getClassLoader(), "htmloutput.html"));
  }
}
