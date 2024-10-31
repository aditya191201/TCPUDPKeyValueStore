package client;

import java.io.IOException;

/**
 * This interface represents a Client which connects to the server and sends request to receive
 * a response.
 */
public interface Client {
  /**
   * Method to connect to the server.
   */
  void connect();

  /**
   * Method to disconnect the server.
   */
  void disconnect();

  /**
   * Method to send request to the server.
   *
   * @param request request to be sent
   * @throws IOException in case of invalid server or port
   */
  void sendRequest(String request) throws IOException;
}
