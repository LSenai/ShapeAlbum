package model.shapes;

import java.awt.geom.Point2D;

/**
 * Oval is a class that represents an oval shape.
 *
 */
public class Oval extends AbstractShape{

  public Oval(String name) {
    super(name);
  }

  /**
   * Constructs an Oval object
   *
   * @param name     the name of the shape
   * @param xRadius    the xRadius of the shape
   * @param yRadius   the yRadius of the shape
   * @param location the location of the shape
   * @param color    the color of the shape
   */
  public Oval(String name, int xRadius, int yRadius, Point2D location, Color color) {
    super(name);

    // cutting the xRadius and yRadius in half for radius
    validatePositive(xRadius);
    this.setWidth(xRadius);
    validatePositive(yRadius);
    this.setHeight(yRadius);

    validateNotNull(location);
    this.moveShape(location);

    validateNotNull(color);
    this.changeColor(color);
  }

  @Override
  public Oval copy() {

    Point2D copyLocation = new Point2D.Double(this.location.getX(), this.location.getY());

    return new Oval(this.name, this.width , this.height , copyLocation,
                    new Color(this.color.getR(), this.color.getG(), this.color.getB()));
  }

  @Override
  public String getType() {
    return "Oval";
  }

  @Override
  public String toString() {
    return "Name: " + this.name + "\nType: " + this.getType() + "\nMin corner: (" + this.location.getX() + ", "
            + this.location.getY() + "), X Radius: " + this.width + ", Y Radius: " + this.height + ", Color: ("
            + this.color.getR() + ", " + this.color.getG() + ", " + this.color.getB() + ")";
  }
}

