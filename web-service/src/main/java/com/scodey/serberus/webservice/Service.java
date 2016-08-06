package com.scodey.serberus.webservice;

import com.scodey.serberus.common.annotations.Controller;
import com.scodey.serberus.common.annotations.Endpoint;

@Controller
public class Service {
  @Endpoint
  public void anEndpoint() {
    System.out.println("endpoint executed");
  }
}
