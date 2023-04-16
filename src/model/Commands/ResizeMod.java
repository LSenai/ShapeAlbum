package model.Commands;

import model.shapes.IShape;

import java.util.LinkedHashMap;

/**
 * This class represents the resize command. Follows the Command design pattern.
 */
public class ResizeMod implements Command {
  private LinkedHashMap<String, IShape> shapes;

  /**
   * Constructor for the resize command.
   * @param shapes the list of shapes that should contain the target shape
   */
  public ResizeMod(LinkedHashMap<String, IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Execute the resize command. String format should be "resize shapeName x y".
   * @param command the command to be executed
   */
  @Override
  public void execute(String command) {
    Util.validateString(command); // check if the string is null or empty
    String[] args = command.split(" ");
    checkExceptions(args);

    IShape shape = shapes.get(args[1]); // extract the target shape
    int x = Integer.parseInt(args[2]);
    int y = Integer.parseInt(args[3]);
    shape.resize(x, y);
  }

  /**
   * Check if the command is valid.
   * @param args the command to be checked
   * @throws IllegalArgumentException if the command is invalid
   * @throws NumberFormatException if the command does not provide whole numbers.
   */
  private void checkExceptions(String[] args) throws IllegalArgumentException, NumberFormatException {

    // check if the target shape is in our shape list
    if (!shapes.containsKey(args[1])) {
      throw new IllegalArgumentException("Invalid command. Shape does not exist in the list");
    }
    if (args.length != 4) {
      throw new IllegalArgumentException("Invalid command. Command must be in the format: resize shapeName x y");
    }
    if (!args[0].equalsIgnoreCase("resize")) {
      throw new IllegalArgumentException("Invalid command. Not a resize command");
    }
    if (!args[2].matches("[0-9]+") || !args[3].matches("[0-9]+")) {
      throw new NumberFormatException("Invalid command. Coordinates must be integers");
    }
  }
}
