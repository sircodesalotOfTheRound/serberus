package com.scodey.serberus.webservice;

import com.scodey.serberus.common.annotations.Controller;
import com.scodey.serberus.common.annotations.Endpoint;

@Controller
public class Service {
  @Endpoint("/something")
  public Object anEndpoint() {
    return "endpoint executed";
  }

  @Endpoint("/another")
  public String anotherEndpoint() {
    return "bar foo the foo bar man";
  }
}
