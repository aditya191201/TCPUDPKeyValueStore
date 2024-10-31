package server;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * This class represents a handler of a server following TCP Protocol. It accepts the client
 * request, processes it and returns the response to the client.
 */
public class TCPHandler extends AbstractHandler {
  protected TCPHandler(int port) {
    super(port);
  }

  @Override
  protected void handle() throws IOException {
    try {
      ServerSocket serverSocket = new ServerSocket(port);
      ServerLogger.log("TCP Server running on port: " + port);
      System.out.println("TCP Server running on port: " + port);
      while (true) {
        try {
          Socket client = serverSocket.accept();
          BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
          String request;
          while ((request = reader.readLine()) != null) {
            ServerLogger.log("Received: " + request + " request from " + client.getInetAddress()
                    + ":" + client.getPort());
            System.out.println("Received: " + request + " request from " + client.getInetAddress()
                    + ":" + client.getPort());
            String response = Response.handleRequest(request);
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
            writer.write(response);
            writer.newLine();
            writer.flush();
            ServerLogger.log("Sent following response: " + response);
            System.out.println("Sent following response: " + response);
          }
        } catch (IOException e) {
          ServerLogger.log("Error while handling client request: " + e.getMessage());
        }
      }
    } catch (IOException e) {
      ServerLogger.log("Failed to start the server: " + e.getMessage());
      throw e;
    }
  }
}
