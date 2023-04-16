package model.Commands;

import model.shapes.Color;
import model.shapes.IShape;

import java.util.LinkedHashMap;

/**
 * Class to change the color of a shape. Follows the Command design pattern.
 */
public class ChangeColorMod implements Command {

  private LinkedHashMap<String, IShape> shapes;

  /**
   * Constructor for the ChangeColorMod class.
   * @param shapes the shapes list that should contain the target shape.
   */
public ChangeColorMod(LinkedHashMap<String, IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Parse command into an array to extract args and RGB values. Validate args and execute change color command.
   * Format of string should be "Color shape R G B".
   * @param command the command to be executed
   */
  @Override
  public void execute(String command) throws IllegalArgumentException, NumberFormatException {
    Util.validateString(command); // check for null or empty string
    String[] args = command.split(" ");
    checkExceptions(args);
    IShape shape = this.shapes.get(args[1]); // extract the target shape

    Color color = getColor(args);
    shape.changeColor(color);
  }

  /**
   * Helper function to extract RGB values from the command.
   * @param args the command split into an array
   * @return the color object
   */
  private Color getColor(String[] args) {
    int r = Integer.parseInt(args[2]);
    int g = Integer.parseInt(args[3]);
    int b = Integer.parseInt(args[4]);
    return new Color(r, g, b);
  }

  /**
   * Check for exceptions in the command.
   * @param args the command split into an array
   * @throws IllegalArgumentException if the command is not in the correct format
   * @throws NumberFormatException if the RGB values are not integers
   */
  private void checkExceptions(String[] args) throws IllegalArgumentException, NumberFormatException {
    // check the command has the correct number of arguments
    if (args.length != 5) {
      throw new IllegalArgumentException("Invalid command. Must be in the format: Color shape # # #");
    }
    // check the command is a change color command
    if (!args[0].equalsIgnoreCase("Color")) {
      throw new IllegalArgumentException("Invalid command. Not a change color command");
    }
    // checks the shape is in the list
    if (!shapes.containsKey(args[1])) {
      throw new IllegalArgumentException("Invalid command. Shape does not exist in the list");
    }
    // check the RGB values are integers
    if (!args[2].matches("[0-9]+") || !args[3].matches("[0-9]+") || !args[4].matches("[0-9]+")) {
      throw new NumberFormatException("Invalid command. RGB values must be integers");
    }
  }
}
