package model.shapes;

import java.awt.geom.Point2D;


/**
 * IShape is an interface for all shape objects.
 *
 */
public interface IShape {
  /**
   * Returns the name of the shape.
   *
   * @return the name of the shape
   */
  String getName();

  /**
   * Sets the name of the shape.
   *
   * @param newName the new name of the shape
   */
  void setName(String newName);

  /**
   * Returns the width of the shape.
   *
   * @return the width of the shape
   */
  int getWidth();

  /**
   * Sets the width of the shape.
   *
   * @param newWidth the new width of the shape
   */
  void setWidth(int newWidth);

  /**
   * Returns the height of the shape.
   *
   * @return the height of the shape
   */
  int getHeight();

  /**
   * Sets the height of the shape.
   *
   * @param newHeight the new height of the shape
   */
  void setHeight(int newHeight);

  /**
   * Returns the coordinates of the shape.
   *
   * @return the coordinates of the shape
   */

  /**
   * Resizes the shape based on new width and height.
   * @param newWidth the new width of the shape
   * @param newHeight the new height of the shape
   */
  void resize(int newWidth, int newHeight);

  Point2D getLocation();

  /**
   * Moves the coordinates to new coordinates.
   * @param newLocation the new coordinates of the shape
   */
  void moveShape(Point2D newLocation);

  /**
   * Returns the color of the shape.
   *
   * @return the color of the shape
   */
  Color getColor();

  /**
   * Sets the color of the shape.
   *
   * @param newColor the new color of the shape
   */
  void changeColor(Color newColor);

  /**
   * Returns the type of the shape.
   *
   * @return the type of the shape
   */
  String getType();

  AbstractShape copy();
}
