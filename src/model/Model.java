package model;

import model.Commands.*;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.ShapeFactory;


import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

/**
 * The Model class represents the model for a photo/shape album application. It contains a list of shapes and a list of
 * snapshots. It also uses the Command pattern to execute commands that modify the shapes list and snapshots list. It
 * delegates the creation of shapes to the ShapeFactory class using a factory method.
 */
public class Model implements IModel {

  private LinkedHashMap<String, IShape> shapes;
  private LinkedHashMap<Snapshot, String> snapshots;

  /**
   * Constructor for the Model class. Initializes the shapes and snapshots lists. These lists both represent the
   * state of the model.
   */
  public Model() {
    shapes = new LinkedHashMap<>();
    snapshots = new LinkedHashMap<>();
  }

  @Override
  public void commandReceiver(String command) throws IllegalArgumentException {
    Util.validateString(command); // check for null or empty string

    // All commands must have at least 2 words.
    if (command.split(" ").length == 1) {
      throw new IllegalArgumentException("Invalid command: " + command);
    }

    String[] args = command.split(" ");
    String name = args[1];
    String theCommand = args[0];

    // execute snapshot command
    if (theCommand.equalsIgnoreCase("snapshot")) {
      Command takeSnapshot = new TakeSnapshotCommand(this.shapes, this.snapshots);
      takeSnapshot.execute(command);
    }
    else if (theCommand.equalsIgnoreCase("shape")) {
      createShape(command);
    } else { // execute shape modification command or snapshot command
      IShape shape = this.shapes.get(name);
      if (shape == null) {
        throw new IllegalArgumentException("There is no shape with name " + name);
      }
      if (theCommand.equalsIgnoreCase("color")) {
        Command shapeMod = new ChangeColorMod(this.shapes);
        shapeMod.execute(command);
      } else if (theCommand.equalsIgnoreCase("move")) {
        Command shapeMod = new MoveShapeMod(this.shapes);
        shapeMod.execute(command);
      } else if (theCommand.equalsIgnoreCase("resize")) {
        Command shapeMod = new ResizeMod(this.shapes);
        shapeMod.execute(command);
      } else if (theCommand.equalsIgnoreCase("remove")) {
        Command removeShape = new RemoveShapeCommand(this.shapes);
        removeShape.execute(command);
      } else if (theCommand.equalsIgnoreCase("snapShot")) {
        Command takeSnapshot = new TakeSnapshotCommand(this.shapes, this.snapshots);
        takeSnapshot.execute(command);
      } else {
        throw new IllegalArgumentException("Error: valid commands must begin with: 'move', 'resize', 'color', "
                                            + "'remove', 'snapshot' and 'shape'");
      }
    }
  }

  /**
   * Method acts as the receiver for all shape creation commands. It receives the command to create a shape and
   * delegates it to the ShapeFactory method for creation. The newly created shape is then added to the shapes list.
   * @param command the command to parse and execute
   */
  private void createShape(String command) {
    String[] args = command.split(" ");
    if (args[0].equalsIgnoreCase("shape")) {
      String shapeType = args[2];
      String name = args[1];

      Point2D location = new Point2D.Double(Integer.parseInt(args[3]), Integer.parseInt(args[4]));
      int width = Integer.parseInt(args[5]);
      int height = Integer.parseInt(args[6]);
      int r = Integer.parseInt(args[7]);
      int g = Integer.parseInt(args[8]);
      int b = Integer.parseInt(args[9]);
      Color color = new Color(r, g, b);

      IShape newShape = ShapeFactory.createShape(shapeType, name, width, height, location, color);
      // add new shape to list of shapes
      addShape(newShape);
    }
    else {
      throw new IllegalArgumentException("Invalid command, this is not a create shape command");
    }
  }

  /**
   * Helper method to add a shape to the shapes list.
   * @param shape the shape to add to the list.
   */
  private void addShape(IShape shape) {
    shapes.put(shape.getName(), shape);
  }

  @Override
  public LinkedHashMap<String, IShape> getShapes() {
    return shapes;
  }

  @Override
  public LinkedHashMap<Snapshot, String> getSnapshots() {
    return snapshots;
  }


  @Override
  public String printShapes() {
    StringBuilder sb = new StringBuilder();
    for (IShape shape : shapes.values()) {
      sb.append(shape.toString() + "\n");
    }
    return sb.toString();
  }

  @Override
  public String printSnapshots() {
    StringBuilder sb = new StringBuilder();
    for (Snapshot snapshot : snapshots.keySet()) {
      sb.append(snapshot.toString() + "\n");
    }
    return sb.toString();
  }

  @Override
  public void clear() {
    this.shapes = new LinkedHashMap<>();
    this.snapshots = new LinkedHashMap<>();
  }
}
