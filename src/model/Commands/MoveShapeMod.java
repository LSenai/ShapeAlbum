package model.Commands;

import model.shapes.IShape;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

/**
 * MoveShapeMod is a class that implements the Command interface. It is used to move a shape object to new coordinates.
 */
public class MoveShapeMod implements Command {

  private LinkedHashMap<String, IShape> shapes;


  /**
   * Constructor for the MoveShapeMod class.
   * @param shape the shape to be moved
   */
  public MoveShapeMod(LinkedHashMap<String, IShape> shapes) {
    this.shapes = shapes;
  }

  /**
   * Parse command to extract new coordinates. Format of string should be "Move shape x y".
   * @param command the command to be executed
   */
  @Override
  public void execute(String command) {
    Util.validateString(command); // check for null or empty string
    String[] args = command.split(" ");
    checkExceptions(args);
    IShape shape = shapes.get(args[1]);

    Point2D newLocation = new Point2D.Double(Integer.parseInt(args[2]), Integer.parseInt(args[3]));
    shape.moveShape(newLocation);
  }

  /**
   * Check if the command is valid.
   * @param args the command to be checked
   * @throws IllegalArgumentException if the command is invalid
   * @throws NumberFormatException if the command does not provide whole numbers.
   */
  private void checkExceptions(String[] args) throws IllegalArgumentException, NumberFormatException {
    // check the command has the proper number of arguments
    if (args.length != 4) {
      throw new IllegalArgumentException("Invalid command. Command must be in the format: Move shape x y");
    }
    // check if the command is a move command
    if (!args[0].equalsIgnoreCase("Move")) {
      throw new IllegalArgumentException("Invalid command. Not a move command");
    }
    // check if the target shape is in our shape list
    if (!this.shapes.containsKey(args[1])) {
      throw new IllegalArgumentException("Invalid command. Shape does not exist in the list");
    }
    // check if the coordinates are integers
    if (!args[2].matches("[0-9]+") || !args[3].matches("[0-9]+")) {
      throw new NumberFormatException("Invalid command. Coordinates must be integers");
    }
  }
}
