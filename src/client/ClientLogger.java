package client;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

/**
 * This class represents a client logger use to log all activities performed by the client app.
 */
public class ClientLogger {
  private static final Logger logger;

  static {
    try {
      logger = Logger.getLogger("ClientLog");
      logger.setUseParentHandlers(false);
      File logDir = new File("client/log");
      if (!logDir.exists()) {
        logDir.mkdirs();
      }
      FileHandler file = new FileHandler("client/log/client.log", true);
      file.setFormatter(new CustomFormatter());
      logger.addHandler(file);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Log the message to the log file.
   *
   * @param message message to be logged
   */
  public static void log(String message) {
    logger.info(message);
  }

  private static class CustomFormatter extends Formatter {
    private final SimpleDateFormat dateFormat =
            new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");

    @Override
    public String format(LogRecord record) {
      StringBuilder sb = new StringBuilder();
      sb.append(dateFormat.format(new Date(record.getMillis()))).append(" [")
              .append(record.getLevel()).append("] ").append(formatMessage(record))
              .append(System.lineSeparator());
      return sb.toString();
    }
  }
}
