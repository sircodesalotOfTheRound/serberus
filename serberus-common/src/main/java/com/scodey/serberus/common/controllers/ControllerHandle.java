package com.scodey.serberus.common.controllers;

import com.scodey.serberus.common.annotations.Controller;
import com.scodey.serberus.common.annotations.Endpoint;
import com.scodey.serberus.common.exceptions.SerberusException;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static jdk.nashorn.internal.runtime.ScriptRuntime.safeToString;

public class ControllerHandle {
  private final Object instance;
  private final Map<String, List<Method>> endpoints;

  public ControllerHandle(Class type)  {
    this.instance = makeInstance(type);
    this.endpoints = caputeEndpoints(type);
  }

  private Object makeInstance(Class type) {
    try {
      return type.newInstance();
    } catch (Exception ex) {
      throw new SerberusException(ex, "Unable to instantiate controller: %s", type.getName());
    }
  }

  private Map<String, List<Method>> caputeEndpoints(Class type) {
    return stream(type.getDeclaredMethods())
      .filter(method -> method.isAnnotationPresent(Endpoint.class))
      .collect(Collectors.groupingBy(method -> {
        Endpoint endpointAnnotation = method.getDeclaredAnnotation(Endpoint.class);
        return endpointAnnotation.value();
      }));
  }

  public static boolean isController(Class type) {
    return type.isAnnotationPresent(Controller.class)
      && (stream(type.getDeclaredConstructors())
        .anyMatch(constructor -> constructor.getParameterCount() == 0));
  }

  public Iterable<String> endpoints() { return this.endpoints.keySet(); }

  public boolean hasEndpoint(String endpoint) {
    return this.endpoints.containsKey(endpoint);
  }

  public Object invokeEndpoint(String endpoint) {
    try {
      return this.endpoints.get(endpoint).get(0).invoke(instance);
    } catch (Throwable ex) {
      throw new SerberusException(ex, "Unable to invoke endpoint");
    }
  }
}
