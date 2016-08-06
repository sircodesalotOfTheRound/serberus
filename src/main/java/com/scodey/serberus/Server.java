package com.scodey.serberus;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

  public void test() throws IOException, InterruptedException {
    ServerSocket server = new ServerSocket(9090);

    while (true) {
      try (Socket socket = server.accept();
           PrintWriter writer = new PrintWriter(socket.getOutputStream());
           BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

        String value;
        while ((value = reader.readLine()) != null) {
          if (value.equals("")) break;
          System.out.println(value);
        }

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

        try (BufferedReader htmlReader = new BufferedReader(new FileReader("htmloutput.html"))) {
          while ((value = htmlReader.readLine()) != null) {
            builder.append(value).append("\n");
          }
        }

        writer.write(builder.toString());
        writer.flush();
        Thread.sleep(1000);
      }

      System.out.println("all done");
    }
  }
}

