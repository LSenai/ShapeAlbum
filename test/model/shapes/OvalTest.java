package model.shapes;

import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.geom.Point2D;

import static org.junit.Assert.*;

public class OvalTest {
  Oval oval;
  private static final int DELTA = 0;

  /**
   * Sets up the test environment, primarily to test getters and setters easily.
   */
  @Before
  public void setUp() {
    oval = new Oval("Oval");
    oval.setWidth(30);
    oval.setHeight(35);
    oval.moveShape(new Point2D.Double(10, 20));
    oval.changeColor(new Color(54, 15, 255));

    Point2D location = new Point2D.Double(10, 20);
    oval.moveShape(location);

  }

  /**
   * Tests the constructor with valid arguments.
   */
  @Test
  public void testConstructorWithValidArguments() {
    Oval oval = new Oval("Oval", 21, 30, new Point2D.Double(10, 20),
                          new Color(255, 255, 255));
    Assert.assertEquals("Oval", oval.getName());
    Assert.assertEquals(21, oval.getWidth(), DELTA);
    Assert.assertEquals(30, oval.getHeight(), DELTA);
    Assert.assertEquals(10, oval.getLocation().getX(), DELTA);
    Assert.assertEquals(20, oval.getLocation().getY(), DELTA);

    Assert.assertEquals(new Color(255, 255, 255), oval.getColor());
    Assert.assertEquals("Oval", oval.getType());
  }

  /**
   * Tests the constructor with invalid name argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidName() {
    new Oval("", 20, 30, new Point2D.Double(10, 10), new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with null name argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithNullName() {
    new Oval(null, 20, 30, new Point2D.Double(10, 10), new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with invalid width argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidWidth() {
    new Oval("myOval", 0, 30, new Point2D.Double(10, 10), new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with invalid height argument.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testConstructorWithInvalidHeight() {
    new Oval("myOval", 20, 0, new Point2D.Double(10, 10), new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with null location argument.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorWithNullLocation() {
    new Oval("myOval", 20, 30, null, new Color(255, 255, 255));
  }

  /**
   * Tests the constructor with null color argument.
   */
  @Test(expected = NullPointerException.class)
  public void testConstructorWithNullColor() {
    new Oval("myOval", 20, 30, new Point2D.Double(10, 10), null);
  }

  /**
   * Tests getName method.
   */
  @Test
  public void getName() {
    assertEquals("Oval", oval.getName());
  }

  /**
   * Tests setName method.
   */
  @Test
  public void setName() {
    oval.setName("NewName");
    assertEquals("NewName", oval.getName());
  }

  /**
   * Tests setName method with invalid name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void setNameWithInvalidName() {
    oval.setName("");
  }

  /**
   * Tests getWidth.
   */
  @Test
  public void getWidth() {
    assertEquals(30, oval.getWidth(), DELTA);
  }

  /**
   * Tests setWidth.
   */
  @Test
  public void setWidth() {
    oval.setWidth(20);
    assertEquals(20, oval.getWidth(), DELTA);
  }

  /**
   * Tests setWidth with invalid width.
   */
  @Test (expected = IllegalArgumentException.class)
  public void setWidthWithInvalidWidth() {
    oval.setWidth(0);
  }

  /**
   * Tests getHeight.
   */
  @Test
  public void getHeight() {
    assertEquals(35, oval.getHeight(), DELTA);
  }

  /**
   * Tests setHeight.
   */
  @Test
  public void setHeight() {
    oval.setHeight(20);
    assertEquals(20, oval.getHeight(), DELTA);
  }

  /**
   * Tests setHeight with invalid height.
   */
  @Test (expected = IllegalArgumentException.class)
  public void setHeightWithInvalidHeight() {
    oval.setHeight(0);
  }

  /**
   * Tests resize.
   */
  @Test
  public void resize() {
    oval.resize(10, 30);
    assertEquals(10, oval.getWidth(), DELTA);
    assertEquals(30, oval.getHeight(), DELTA);
  }

  /**
   * Tests resize with invalid width.
   */
  @Test
  public void getLocation() {
    assertEquals(new Point2D.Double(10, 20), oval.getLocation());
  }

  /**
   * Tests moveShape.
   */
  @Test
  public void moveShape() {
    oval.moveShape(new Point2D.Double(20, 30));
    assertEquals(new Point2D.Double(20, 30), oval.getLocation());
  }

  /**
   * Tests moveShape with null location.
   */
  @Test(expected = NullPointerException.class)
  public void moveShapeWithNullLocation() {
    oval.moveShape(null);
  }

  /**
   * Tests getColor.
   */
  @Test
  public void getColor() {
    assertEquals(new Color(54, 15, 255), oval.getColor());
  }

  /**
   * Tests changeColor.
   */
  @Test
  public void changeColor() {
    oval.changeColor(new Color(255, 255, 255));
    assertEquals(new Color(255, 255, 255), oval.getColor());
  }

  /**
   * Tests changeColor with null color.
   */
  @Test(expected = NullPointerException.class)
  public void changeColorWithNullColor() {
    oval.changeColor(null);
  }

  /**
   * Tests getType.
   */
  @Test
  public void getType() {
    assertEquals("Oval", oval.getType());
  }


  /**
   * Tests the constructor with invalid name argument.
   */
  @Test
  public void testEquals() {
    Oval equalOval = new Oval("Oval");
    equalOval.setWidth(30);
    equalOval.setHeight(35);
    equalOval.moveShape(new Point2D.Double(10, 20));
    equalOval.changeColor(new Color(54, 15, 255));
    Point2D location = new Point2D.Double(10, 20);
    equalOval.moveShape(location);
    assertEquals(equalOval, oval);

    // change a field and assert not equal
    equalOval.setName("NotEqual");
    assertNotEquals(equalOval, oval);
  }

  /**
   * test copy method.
   */
  @Test
  public void copy() {
    IShape copyOval = oval.copy();
    assertEquals(copyOval, oval);

    // change a field and assert not equal
    copyOval.setHeight(2);
    assertNotEquals(copyOval, oval);
  }

  /**
   * test toString method.
   */
  @Test
  public void testToString() {
    assertEquals("""
        Name: Oval
        Type: Oval
        Min corner: (10.0, 20.0), X Radius: 30, Y Radius: 35, Color: (54, 15, 255)""", oval.toString());
  }

  /**
   * test getType method.
   */
  @Test
  public void testGetType() {
    assertEquals("Oval", oval.getType());
  }
}