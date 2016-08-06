package com.scodey.serberus.server;

import com.scodey.serberus.common.request.Request;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
  public Server(String[] args) {

  }

  public void start() throws IOException, InterruptedException {
    ServerSocket server = new ServerSocket(9090);

    while (true) {
      try (Socket socket = server.accept()) {
        handleConnection(socket);
        System.out.println("all done");
      }
    }
  }

  private void handleConnection(Socket socket) throws IOException, InterruptedException {
    try (
      PrintWriter writer = new PrintWriter(socket.getOutputStream())) {
      Request request = new Request(socket);

      Thread.sleep(100);
      String[] response = {
        "HTTP/1.1 200 OK",
        "Content-Type: text/html",
        "",
      };

      StringBuilder builder = new StringBuilder();
      for (String line : response) {
        builder.append(line).append("\n");
      }

      String value;
      try (BufferedReader htmlReader = new BufferedReader(new FileReader("htmloutput.html"))) {
        while ((value = htmlReader.readLine()) != null) {
          builder.append(value).append("\n");
        }
      }

      writer.write(builder.toString());
      writer.flush();
    }
  }

  public static void run(String[] args) throws IOException, InterruptedException {
    new Server(args).start();
  }
}

