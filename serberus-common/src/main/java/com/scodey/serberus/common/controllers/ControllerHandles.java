package com.scodey.serberus.common.controllers;

import java.util.HashMap;
import java.util.Map;

public class ControllerHandles {
  private final Map<String, ControllerHandle> handles;

  public ControllerHandles(Iterable<ControllerHandle> handles) {
    this.handles = collectHandles(handles);
  }

  private Map<String, ControllerHandle> collectHandles(Iterable<ControllerHandle> handles) {
    Map<String, ControllerHandle> mapping = new HashMap<>();
    for (ControllerHandle handle : handles) {
      for (String endpoint : handle.endpoints()) {
        mapping.put(endpoint, handle);
      }
    }

    return mapping;
  }

  public boolean hasHandleFor(String endpoint) {
    return this.handles.containsKey(endpoint);
  }

  public Object invokeHandle(String endpoint) {
    // Todo: make this return Http Content.
    return this.handles.get(endpoint).invokeEndpoint(endpoint);
  }
}
