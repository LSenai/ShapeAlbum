package model.Commands;

import model.shapes.IShape;

import java.util.LinkedHashMap;

/**
 * Class to add a shape to the shapes list. Follows the Command design pattern.
 */
public class RemoveShapeCommand implements Command {

  private LinkedHashMap<String, IShape> shapes;

  /**
   * Constructor for the AddShapeCommand class.
   * @param shapes the list of shapes to remove the shape from
   */
  public RemoveShapeCommand(LinkedHashMap<String, IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Executes the command to remove a shape to the shapes list. String format should be "Remove shapeName".
   * @param command the command to be executed.
   */
  @Override
  public void execute(String command) {
    Util.validateString(command); // check for null or empty string
    String[] args = command.split(" ");
    checkExceptions(args);

    // remove the shape from the list using the key (shape name) as the identifier
    String shapeName = args[1];
    shapes.remove(shapeName);
  }

  private void checkExceptions(String[] args) throws IllegalArgumentException {
    // checks there are two arguments in the command
    if (args.length != 2) {
      throw new IllegalArgumentException("Invalid command. Must be in the format: Remove shape");
    }
    // checks the first argument is "Remove"
    if (!args[0].equalsIgnoreCase("Remove")) {
      throw new IllegalArgumentException("Invalid command. Not a remove shape command");
    }
    // checks the shape is in the list
    if (!shapes.containsKey(args[1])) {
      throw new IllegalArgumentException("Invalid command. Shape does not exist in the list");
    }
  }
}
