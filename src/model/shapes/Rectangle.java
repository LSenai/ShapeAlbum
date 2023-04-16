package model.shapes;

import java.awt.geom.Point2D;

/**
 * Rectangle is a class that represents a rectangle shape.
 *
 */
public class Rectangle extends AbstractShape {

  /**
   * Constructor for the Rectangle class.
   *
   * @param name the name of the shape
   */
  public Rectangle(String name) {
    super(name);
  }
  /**
   * Overloaded constructor.
   *
   * @param name     the name of the shape
   * @param width    the width of the shape
   * @param height   the height of the shape
   * @param location the location of the shape
   * @param color    the color of the shape
   */
  public Rectangle(String name, int width, int height, Point2D location, Color color) {
    super(name);

    validatePositive(width);
    this.setWidth(width);
    validatePositive(height);
    this.setHeight(height);

    validateNotNull(location);
    this.moveShape(location);

    validateNotNull(color);
    this.changeColor(color);
  }

  @Override
  public String getType() {
    return "Rectangle";
  }

  @Override
  public Rectangle copy() {
    Point2D copyLocation = new Point2D.Double(this.location.getX(), this.location.getY());

    return new Rectangle(this.name, this.width , this.height , copyLocation,
                    new Color(this.color.getR(), this.color.getG(), this.color.getB()));
  }

}