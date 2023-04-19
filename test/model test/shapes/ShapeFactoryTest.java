package model.shapes;

import model.shapes.*;
import org.junit.Test;
import org.junit.Assert;
import java.awt.geom.Point2D;

public class ShapeFactoryTest {

  @Test
  public void testCreateShape() {
    // Test creating a Rectangle
    String name = "rectangle1";
    int width = 50;
    int height = 40;
    Point2D location = new Point2D.Double(10, 20);
    Color color = new Color(0, 0, 0);
    IShape rectangle = ShapeFactory.createShape("rectangle", name, width, height, location, color);

    // test all fields instantiated correctly
    Assert.assertNotNull(rectangle);
    Assert.assertTrue(rectangle instanceof Rectangle);
    Assert.assertEquals(name, rectangle.getName());
    Assert.assertEquals(width, rectangle.getWidth());
    Assert.assertEquals(height, rectangle.getHeight());
    Assert.assertEquals(location, rectangle.getLocation());
    Assert.assertEquals(color, rectangle.getColor());

    // Test creating an Oval
    name = "oval1";
    width = 30;
    height = 35;
    location = new Point2D.Double(50, 60);
    color = new Color(255, 255, 255);
    IShape oval = ShapeFactory.createShape("oval", name, width, height, location, color);

    // test all fields instantiated correctly
    Assert.assertNotNull(oval);
    Assert.assertTrue(oval instanceof Oval);
    Assert.assertEquals(name, oval.getName());
    Assert.assertEquals(width, oval.getWidth());
    Assert.assertEquals(height, oval.getHeight());
    Assert.assertEquals(location, oval.getLocation());
    Assert.assertEquals(color, oval.getColor());
  }

  /**
   * Tests creating a shape of an unknown type will raise an error.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testUnknownShape() {
    ShapeFactory.createShape("unknown", "name", 10, 20,
        new Point2D.Double(10, 20), new Color(0, 0, 0));
  }

}
