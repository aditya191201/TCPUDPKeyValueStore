package server;

import java.io.IOException;

/**
 * This class represents the Server app which starts the server using protocol and port number
 * mentioned in Command Line Arguments.
 */
public class ServerApp {
  /**
   * Main function to start the server.
   *
   * @param args protocol as args[0] and port as args[1]
   * @throws IOException in case of invalid IO operations
   */
  public static void main(String[] args) throws IOException {
    if (args.length < 2) {
      System.out.println("Please pass correct arguments: Protocol and Port number to be used");
      return;
    }
    String protocol = args[0];
    int port = Integer.parseInt(args[1]);
    AbstractHandler handler;
    if (protocol.toLowerCase().equals("tcp")) {
      handler = new TCPHandler(port);
    } else if (protocol.toLowerCase().equals("udp")) {
      handler = new UDPHandler(port);
    } else {
      System.out.println("Invalid protocol specified. Please use TCP or UDP protocol");
      return;
    }
    handler.handle();
  }
}
