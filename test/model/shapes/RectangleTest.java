package model.shapes;

import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Rectangle;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

/**
 * Test for the Rectangle class.
 */
public class RectangleTest {
  Rectangle rectangle;
  private static final int DELTA = 0;

  /**
   * Sets up the test environment, primarily to test getters and setters easily.
   */
  @Before
  public void setUp() {
    rectangle = new Rectangle("Rectangle");
    rectangle.setWidth(30);
    rectangle.setHeight(35);
    rectangle.moveShape(new Point2D.Double(10, 20));
    rectangle.changeColor(new Color(54, 15, 255));

    Point2D location = new Point2D.Double(10, 20);
    rectangle.moveShape(location);

  }

  /**
   * Tests the constructor with valid arguments.
   */
  @Test
  public void testConstructorWithValidArguments() {
    Rectangle rectangle = new Rectangle("Rectangle", 21, 30, new Point2D.Double(10, 20),
        new Color(255, 255, 255));
    Assert.assertEquals("Rectangle", rectangle.getName());
    Assert.assertEquals(21, rectangle.getWidth(), DELTA);
    Assert.assertEquals(30, rectangle.getHeight(), DELTA);
    Assert.assertEquals(10, rectangle.getLocation().getX(), DELTA);
    Assert.assertEquals(20, rectangle.getLocation().getY(), DELTA);

    Assert.assertEquals(new Color(255, 255, 255), rectangle.getColor());
    Assert.assertEquals("Rectangle", rectangle.getType());
  }

  /**
   * Tests the constructor with invalid name argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidName() {
    new Rectangle("", 20, 30, new Point2D.Double(10, 10), new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with null name argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new Rectangle(null, 20, 30, new Point2D.Double(10, 10), new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with invalid width argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidWidth() {
    new Rectangle("myRectangle", 0, 30, new Point2D.Double(10, 10),
                  new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with invalid height argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidHeight() {
    new Rectangle("myRectangle", 20, 0, new Point2D.Double(10, 10),
                  new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with null location argument.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorWithNullLocation() {
    new Rectangle("myRectangle", 20, 30, null, new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with null color argument.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorWithNullColor() {
    new Rectangle("myRectangle", 20, 30, new Point2D.Double(10, 10), null);
  }

  /**
   * Tests getName method.
   */
  @Test
  public void getName() {
    assertEquals("Rectangle", rectangle.getName());
  }

  /**
   * Tests setName method.
   */
  @Test
  public void setName() {
    rectangle.setName("NewName");
    assertEquals("NewName", rectangle.getName());
  }

  /**
   * Tests setName method with invalid name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setNameWithInvalidName() {
    rectangle.setName("");
  }

  /**
   * Tests getWidth.
   */
  @Test
  public void getWidth() {
    assertEquals(30, rectangle.getWidth(), DELTA);
  }

  /**
   * Tests setWidth.
   */
  @Test
  public void setWidth() {
    rectangle.setWidth(20);
    assertEquals(20, rectangle.getWidth(), DELTA);
  }

  /**
   * Tests setWidth with invalid width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setWidthWithInvalidWidth() {
    rectangle.setWidth(0);
  }

  /**
   * Tests getHeight.
   */
  @Test
  public void getHeight() {
    assertEquals(35, rectangle.getHeight(), DELTA);
  }

  /**
   * Tests setHeight.
   */
  @Test
  public void setHeight() {
    rectangle.setHeight(20);
    assertEquals(20, rectangle.getHeight(), DELTA);
  }

  /**
   * Tests setHeight with invalid height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setHeightWithInvalidHeight() {
    rectangle.setHeight(0);
  }

  /**
   * Tests resize.
   */
  @Test
  public void resize() {
    rectangle.resize(10, 30);
    assertEquals(10, rectangle.getWidth(), DELTA);
    assertEquals(30, rectangle.getHeight(), DELTA);
  }

  /**
   * Tests resize with invalid width.
   */
  @Test(expected = IllegalArgumentException.class)
  public void resizeWithInvalidWidth() {
    rectangle.resize(0, 30);
  }

  /**
   * Tests resize with invalid height.
   */
  @Test(expected = IllegalArgumentException.class)
  public void resizeWithInvalidHeight() {
    rectangle.resize(10, 0);
  }

  /**
   * Tests getLocation.
   */
  @Test
  public void getLocation() {
    assertEquals(new Point2D.Double(10, 20), rectangle.getLocation());
  }

  /**
   * Tests moveShape.
   */
  @Test
  public void moveShape() {
    rectangle.moveShape(new Point2D.Double(20, 30));
    assertEquals(new Point2D.Double(20, 30), rectangle.getLocation());
  }

  /**
   * Tests moveShape with null location.
   */
  @Test(expected = NullPointerException.class)
  public void moveShapeWithNullLocation() {
    rectangle.moveShape(null);
  }


  /**
   * Tests getColor.
   */
  @Test
  public void getColor() {
    assertEquals(new Color(54, 15, 255), rectangle.getColor());
  }

  /**
   * Tests changeColor.
   */
  @Test
  public void changeColor() {
    rectangle.changeColor(new Color(255, 255, 255));
    assertEquals(new Color(255, 255, 255), rectangle.getColor());
  }

  /**
   * Tests changeColor with null color.
   */
  @Test(expected = NullPointerException.class)
  public void changeColorWithNullColor() {
    rectangle.changeColor(null);
  }

  /**
   * Tests getType.
   */
  @Test
  public void getType() {
    assertEquals("Rectangle", rectangle.getType());
  }

  /**
   * Tests the constructor with invalid name argument.
   */
  @Test
  public void testEquals() {
    Rectangle equalRectangle = new Rectangle("Rectangle");
    equalRectangle.setWidth(30);
    equalRectangle.setHeight(35);
    equalRectangle.moveShape(new Point2D.Double(10, 20));
    equalRectangle.changeColor(new Color(54, 15, 255));
    Point2D location = new Point2D.Double(10, 20);
    equalRectangle.moveShape(location);
    assertEquals(equalRectangle, rectangle);

    // change a field and assert not equal
    equalRectangle.setName("NotEqual");
    assertNotEquals(equalRectangle, rectangle);
  }

  /**
   * test copy method.
   */
  @Test
  public void copy() {
    IShape copyRectangle = rectangle.copy();
    assertEquals(copyRectangle, rectangle);

    // change a field and assert not equal
    copyRectangle.setName("NotEqual");
    assertNotEquals(copyRectangle, rectangle);
  }

  /**
   * test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("""
        Name: Rectangle
        Type: Rectangle
        Min corner: (10.0, 20.0), Width: 30, Height: 35, Color: (54, 15, 255)""", rectangle.toString());
  }

  /**
   * test getType method.
   */
  @Test
  public void testGetType() {
    assertEquals("Rectangle", rectangle.getType());
  }

}