package server;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * This class represents a handler of a server following UDP Protocol. It accepts the client
 * request, processes it and returns the response to the client.
 */
public class UDPHandler extends AbstractHandler{
  protected UDPHandler(int port) {
    super(port);
  }

  @Override
  protected void handle() throws IOException {
    try {
      DatagramSocket socket = new DatagramSocket(port);
      ServerLogger.log("UDP Server is running on port: " + port);
      System.out.println("UDP Server is running on port: " + port);
      byte[] buffer = new byte[1000];
      while (true) {
        DatagramPacket requestPacket = new DatagramPacket(buffer, buffer.length);
        try {
          socket.receive(requestPacket);
          String clientRequest = new String(requestPacket.getData(),
                  0, requestPacket.getLength());
          ServerLogger.log("Received request: " + requestPacket
                  + " from " + requestPacket.getAddress() + " : " + requestPacket.getPort());
          System.out.println("Received request: " + requestPacket
                  + " from " + requestPacket.getAddress() + " : " + requestPacket.getPort());
          String response = Response.handleRequest(clientRequest);
          byte[] responseBytes = response.getBytes();
          DatagramPacket responsePacket = new DatagramPacket(responseBytes, responseBytes.length,
                  requestPacket.getAddress(), requestPacket.getPort());
          socket.send(responsePacket);
          ServerLogger.log("Response Sent: " + response);
          System.out.println("Response Sent: " + response);
        } catch (IOException e) {
          ServerLogger.log("Malformed request from: " + requestPacket.getAddress()
                  + " : " + requestPacket.getPort());
        }
      }
    } catch (IOException e) {
      ServerLogger.log("Failed to start the server: " + e.getMessage());
      throw e;
    }
  }
}
