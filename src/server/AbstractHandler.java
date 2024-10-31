package server;

import java.io.IOException;

/**
 * Abstract class to handle common functionality offered by different types of servers.
 */
public abstract class AbstractHandler {
  protected final int port;
  protected AbstractHandler(int port) {
    this.port = port;
  }
  protected abstract void handle() throws IOException;
}
