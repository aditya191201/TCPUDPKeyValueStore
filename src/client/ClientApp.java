package client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * This class represents entry point of a client application. It starts the client application
 * with the specified protocol, ip address or port number.
 */
public class ClientApp {
  /**
   * Main function to start the client application
   *
   * @param args args[0] = protocol (TCP or UDP), args[1] = hostname, args[2] = port number
   * @throws Exception in case of error in setting up client
   */
  public static void main(String[] args) throws Exception {
    try {
      if (args.length != 3) {
        System.out.println("Invalid arguments passed");
        return;
      }
      String protocol = args[0];
      String host = args[1];
      int port = Integer.parseInt(args[2]);
      Client client;
      if (protocol.toLowerCase().equals("tcp")) {
        client = new TCPClient(host, port);
      } else if (protocol.toLowerCase().equals("udp")) {
        client = new UDPClient(host, port);
      } else {
        System.out.println("Unknown port");
        return;
      }
      client.connect();
      sendRequests(client);
      BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
      String input = "";
      while (!input.equals("exit")) {
        System.out.println("Enter requests (For e.g., PUT key value, GET key, DELETE key) " +
                "or type exit to quit:");
        input = reader.readLine().trim();

        if (!input.equals("exit")) {
          client.sendRequest(input);
        }
      }
      client.disconnect();
    } catch (Exception e) {
      System.out.println(e.getMessage());
      ClientLogger.log(e.getMessage());
    }

  }

  private static void sendRequests(Client client) throws IOException {
    //Pre-populating server
    client.sendRequest("PUT Boston Massachusetts");
    client.sendRequest("PUT NewYork NewYork");
    client.sendRequest("PUT Chicago Illinois");
    client.sendRequest("PUT SanDiego California");
    client.sendRequest("PUT Austin Texas");
    //5 PUT Requests
    client.sendRequest("PUT Detroit Michigan");
    client.sendRequest("PUT Denver Colorado");
    client.sendRequest("PUT JerseyCity NewJersey");
    client.sendRequest("PUT Bloomington Indianapolis");
    client.sendRequest("PUT Seattle Washington");
    //5 GET Requests
    client.sendRequest("GET Boston");
    client.sendRequest("GET Denver");
    client.sendRequest("GET Austin");
    client.sendRequest("GET Bloomington");
    client.sendRequest("GET Chicago");
    client.sendRequest("GET Tampa");
    //5 DELETE Requests
    client.sendRequest("DELETE Austin");
    client.sendRequest("DELETE Detroit");
    client.sendRequest("DELETE SanDiego");
    client.sendRequest("DELETE Bloomington");
    //should log key not found
    client.sendRequest("DELETE Houston");
  }
}
