package com.scodey.serberus.server;

import com.scodey.serberus.common.controllers.ControllerHandle;
import com.scodey.serberus.common.controllers.ControllerHandles;
import com.scodey.serberus.common.reflect.JarInfo;
import com.scodey.serberus.common.request.Request;
import com.scodey.serberus.common.response.Response;
import com.scodey.serberus.common.response.ResponseCode;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.scodey.serberus.common.tools.functional.Functional.stream;

public class Server {
  private final ControllerHandles controllerHandles;

  public Server(String[] args) {
    this.controllerHandles = host(args);
  }

  private ControllerHandles host(String[] args) {
    List<ControllerHandle> handles = new ArrayList<>();
    for (String file : args) {
      JarInfo jarInfo = new JarInfo(new File(file));
      handles.addAll(stream(jarInfo.classes())
        .filter(ControllerHandle::isController)
        .map(ControllerHandle::new)
        .collect(Collectors.toList()));
    }

    return new ControllerHandles(handles);
  }

  private void start() throws IOException, InterruptedException {
    ServerSocket server = new ServerSocket(9090);

    while (true) {
      try (Socket socket = server.accept()) {
        Thread.sleep(100);
        Response response = buildResponse(socket);
        response.send(socket.getOutputStream());

        System.out.println(response.responseCode());
        Thread.sleep(100);
      }
    }
  }

  private Response buildResponse(Socket socket) throws IOException {
    Request request = new Request(socket.getInputStream());

    if (this.controllerHandles.hasHandleFor(request.uri())) {
      Response.Builder responseBuilder = new Response.Builder(ResponseCode.OK);
      Object responseValue = this.controllerHandles.invokeHandle(request.uri());
      responseBuilder.println(responseValue.toString());

      return responseBuilder.build();
    } else {
      Response.Builder responseBuilder = new Response.Builder(ResponseCode.NOT_FOUND);
      responseBuilder.println("404 No Content Found");

      return responseBuilder.build();
    }

  }

  public static void run(String[] args) throws IOException, InterruptedException {
    new Server(args).start();
  }
}

