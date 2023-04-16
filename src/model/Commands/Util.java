package model.Commands;

/**
 * This class is a utility class that contains methods that are used in multiple classes.
 */
public final class Util {
  /**
   * This method checks if the given string is null or empty
   * @param string the string to check
   */
  public static void validateString(String string) {
    if (string == null || string.isEmpty()) {
      throw new IllegalArgumentException("String cannot be null or empty.");
    }
  }
}
