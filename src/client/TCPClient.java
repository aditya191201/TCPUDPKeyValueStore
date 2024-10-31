package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.SocketTimeoutException;

/**
 * This class represents a client application following TCP protocol. It sends request to the TCP
 * server and expects a response.
 */
public class TCPClient implements Client {
  private Socket socket;
  private BufferedReader reader;
  private PrintWriter writer;
  private final String host;
  private final int port;
  private static final int TIMEOUT = 7000;


  public TCPClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void sendRequest(String request) throws IOException {
    if (socket == null || socket.isClosed()) {
      connect();
    }
    writer.println(request);
    try {
      String response = reader.readLine();
      if (response == null) {
        throw new SocketTimeoutException("No response from server.");
      }
      ClientLogger.log("Response from server: " + response);
      System.out.println("Response from server: " + response);
    } catch (SocketTimeoutException e) {
      ClientLogger.log("TTimeout: Server not responding for request: " + request);
    }
  }

  @Override
  public void connect() {
    try {
      socket = new Socket(host, port);
      socket.setSoTimeout(TIMEOUT);
      reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
      writer = new PrintWriter(socket.getOutputStream(), true);
      ClientLogger.log("Connected to TCP Server");
      System.out.println("Connected to TCP Server");
    } catch (Exception e) {
      ClientLogger.log("Error connecting to TCP Server: " + e.getMessage());
    }
  }

  @Override
  public void disconnect() {
    try {
      if (reader != null) {
        reader.close();
      }
      if (writer != null) {
        writer.close();
      }
      if (socket != null && !socket.isClosed()) {
        socket.close();
      }
      ClientLogger.log("Disconnected from TCP server");
    } catch (IOException e) {
      ClientLogger.log("Error disconnecting: " + e.getMessage());
    } finally {
      reader = null;
      writer = null;
      socket = null;
    }
  }

}
