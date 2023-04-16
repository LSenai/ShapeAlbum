package model.shapes;

import java.awt.geom.Point2D;

/**
  * Factory class for creating shapes.
 */
public class ShapeFactory {

  /**
   * Creates a shape of the given type.
   * @param type the type of shape to create
   * @param name the name of the shape
   * @param width the width of the shape
   * @param height the height of the shape
   * @param location the location of the shape
   * @param color the color of the shape
   * @return the shape
   */
  public static IShape createShape(String type, String name, int width, int height, Point2D location, Color color) {
    if (type.equalsIgnoreCase("rectangle")) {
      return new Rectangle(name, width, height, location, color);
    } else if (type.equalsIgnoreCase("oval")) {
      return new Oval(name, width, height, location, color);
    } else {
      throw new IllegalArgumentException("Unknown shape type: " + type);
    }
  }
}
