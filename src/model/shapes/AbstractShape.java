package model.shapes;

import java.awt.geom.Point2D;

/**
 * AbstractShape is an abstract class that implements the IShape interface.
 */
public class AbstractShape implements IShape {

  String name;
  int width;
  int height;
  Point2D location;
  Color color;

  /**
   * Basic constructor for shape. Makes use of a simple constructor that only takes in a name in case we add more
   * shapes in the future that have different attributes.
   *
   * @param name the name of the shape
   */
  public AbstractShape(String name) {
    validateString(name);
    this.name = name;
  }

  /**
   * Validates that the string is not null or empty.
   * @param string the string to validate
   */
  protected void validateString(String string) {
    if (string == null || string.isEmpty()) {
      throw new IllegalArgumentException("String cannot be null or empty.");
    }
  }

  /**
   * Validates that the object is not null.
   * @param object the object to validate
   */
  protected void validateNotNull(Object object) {
    if (object == null) {
      throw new NullPointerException("Object cannot be null.");
    }
  }

  /**
   * Validates that the value is greater than 0.
   * @param value the value to validate
   */
  protected void validatePositive(int value) {
    if (value <= 0) {
      throw new IllegalArgumentException("Value must be greater than 0.");
    }
  }

  @Override
  public String getName() {
    return this.name;
  }

  @Override
  public void setName(String newName) {
    validateString(newName);
    this.name = newName;
  }

  @Override
  public int getWidth() {
    return this.width;
  }

  @Override
  public void setWidth(int newWidth) {
    validatePositive(newWidth);
    this.width = newWidth;
  }

  @Override
  public int getHeight() {
    return this.height;
  }

  @Override
  public void setHeight(int newHeight) {
    validatePositive(newHeight);
    this.height = newHeight;
  }

  @Override
  public void resize(int newWidth, int newHeight) {
    validatePositive(newWidth);
    validatePositive(newHeight);
    this.width = newWidth;
    this.height = newHeight;
  }

  @Override
  public Point2D getLocation() {
    return this.location;
  }

  @Override
  public void moveShape(Point2D newLocation) {
    validateNotNull(newLocation);
    this.location = newLocation;
  }

  @Override
  public Color getColor() {
    return this.color;
  }

  @Override
  public void changeColor(Color newColor) {
    validateNotNull(newColor);
    this.color = newColor;
  }

  @Override
  public String getType() {
    return null;
  }

  /**
   * A shape is equal to another shape if they have the same name, width, height, and color. They are still considered
   * equal if they have different locations.
   * @param o the object to compare to.
   * @return true if the objects are equal, false otherwise.
   */
  @Override
  public boolean equals(Object o) {
    if (o == null) {
      return false;
    }
    if (o == this) {
      return true;
    }
    if (o.getClass() != this.getClass()) {
      return false;
    }
    IShape other = (IShape) o;
    return (this.name.equals(other.getName()) && this.width == other.getWidth()
        && this.height == other.getHeight() && this.color.equals(other.getColor()));
  }

  @Override
  public AbstractShape copy() {
    AbstractShape copy = new AbstractShape(this.name);
    copy.setWidth(this.width);
    copy.setHeight(this.height);
    copy.moveShape(new Point2D.Double(this.location.getX(), this.location.getY()));
    copy.changeColor(new Color(this.color.getR(), this.color.getG(), this.color.getB()));
    return copy;
  }

  @Override
  public int hashCode() {
    return this.name.hashCode() + (int) this.width + (int) this.height + this.color.hashCode();
  }

  /**
   * toString format:
   * Name: R
   * Type: rectangle
   * Min corner: (200.0,200.0), Width: 50.0, Height: 100.0, Color: (1.0,0.0,0.0)
   */
  @Override
  public String toString() {
    String className = this.getClass().getSimpleName();

    return ("Name: " + this.name + "\n" + "Type: " + className + "\n" + "Min corner: ("
        + this.location.getX() + ", " + this.location.getY() + "), Width: " + this.width + ", Height: "
        + this.height + ", Color: "+ this.color.toString());
  }
}
