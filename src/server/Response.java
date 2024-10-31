package server;

/**
 * This class represents the response sent by the server to the client for a particular request.
 */
public class Response {
  /**
   * Handles the request (GET, PUT, DELETE) and sends appropriate response to the client.
   *
   * @param request request sent by the client
   * @return response from server
   */
  public static String handleRequest(String request) {
    String[] splitRequest = request.split(" ");
    int length = splitRequest.length;
    if (length < 2) {
      return "Incorrect Request";
    }
    String command = splitRequest[0].toLowerCase();
    switch (command) {
      case "put":
        if (splitRequest.length < 3) {
          return "Incorrect Request";
        }
        if(KeyValue.put(splitRequest[1], splitRequest[2])) {
          return "Key-Value Inserted";
        } else {
          return "Invalid type of key or value. Key and Value Must Be String or characters";
        }
      case "get":
        return KeyValue.get(splitRequest[1]);
      case "delete":
        return KeyValue.delete(splitRequest[1]);
      default:
        return "Unknown Request";
    }
  }
}
