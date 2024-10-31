package server;

import java.util.HashMap;
import java.util.Map;

/**
 * This class represents Key Value store which stores all the keys and corresponding values in
 * the server. It offers methods to get, put and delete keys in the map.
 */
public class KeyValue {
  private static final Map<String, String> store = new HashMap<>();

  /**
   * Method to add a key to the key-value store.
   *
   * @param key   key to be added
   * @param value value of added key
   */
  public static boolean put(String key, String value) {
    if (isInteger(value) || isBoolean(value) || isInteger(key) || isBoolean(key)) {
      return false;
    } else {
      store.put(key, value);
      return true;
    }
  }

  /**
   * Method to get value of a particular key.
   *
   * @param key key
   * @return value of the key
   */
  public static String get(String key) {
    return store.getOrDefault(key, "Key Not Found");
  }

  /**
   * Method to delete a key from key-value store.
   *
   * @param key key to be deleted
   * @return deleted key
   */
  public static String delete(String key) {
    if (store.containsKey(key)) {
      store.remove(key);
      return "Deleted " + key;
    } else {
      return "Key Not Found";
    }
  }

  /**
   * Method to check if passed string is integer or not.
   *
   * @param s string passed
   * @return true if it is integer else false
   */
  private static boolean isInteger(String s) {
    try {
      Integer.parseInt(s);
      return true;
    } catch (Exception e) {
      return false;
    }
  }

  /**
   * Method to check if passed string is boolean or not
   *
   * @param s string passed
   * @return true if it is boolean else false
   */
  private static boolean isBoolean(String s) {
    return s.toLowerCase().equals("true") || s.toLowerCase().equals("false");
  }
}
