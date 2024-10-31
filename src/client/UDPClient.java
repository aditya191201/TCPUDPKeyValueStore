package client;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.SocketTimeoutException;

/**
 * This class represents a client application following UDP protocol. It sends request to the UDP
 * server and expects a response.
 */
public class UDPClient implements Client{
  private DatagramSocket socket;
  private final String host;
  private final int port;
  private static final int TIMEOUT = 7000;
  public UDPClient(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public void sendRequest(String request) throws IOException {
    if(socket == null || socket.isClosed()) {
      connect();
    }
    byte[] buffer = request.getBytes();
    InetAddress aHost = InetAddress.getByName(host);
    DatagramPacket packet = new DatagramPacket(buffer, buffer.length, aHost, port);
    socket.send(packet);
    byte[] received = new byte[1000];
    DatagramPacket reply = new DatagramPacket(received, received.length);
    try {
      socket.receive(reply);
      String response = new String(reply.getData(), 0, reply.getLength());
      ClientLogger.log("Response from server: " + response);
      System.out.println("Response from server: " + response);
    } catch (SocketTimeoutException e) {
      ClientLogger.log("Timeout: Server not responding for request: " + request);
    } catch (IOException e) {
      ClientLogger.log("Malformed response received.");
    }
  }

  @Override
  public void connect() {
    try {
      socket = new DatagramSocket();
      socket.setSoTimeout(TIMEOUT);
      ClientLogger.log("Connected to UDP Server");
      System.out.println("Connected to UDP Server");
    } catch (Exception e) {
      ClientLogger.log("Error connecting to server: " + e.getMessage());
    }
  }

  @Override
  public void disconnect() {
    socket.close();
    ClientLogger.log("Disconnected from UDP server");
  }
}
