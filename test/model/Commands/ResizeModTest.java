package model.Commands;

import model.Commands.ResizeMod;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

/**
 * Test class for the ResizeMod class.
 */
public class ResizeModTest {
  private IShape rect;
  private IShape oval;
  private ResizeMod resizeMod;
  private LinkedHashMap<String, IShape> shapes;

  /**
   * Set up the test environment.
   */
  @Before
  public void setUp() {
    rect = new Rectangle("rect", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    oval = new Oval("oval", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));

    shapes = new LinkedHashMap<String, IShape>();
    shapes.put("rect", rect);
    shapes.put("oval", oval);
    resizeMod = new ResizeMod(shapes);
  }

  /**
   * Test the execute method of the ResizeMod class.
   */
  @Test
  public void testExecute() {
    // test to see the command works
    resizeMod.execute("resize rect 50 50");
    resizeMod.execute("resize oval 50 50");

    // Check if the shape's size has been updated
    assertEquals(50, rect.getWidth());
    assertEquals(50, rect.getHeight());
    assertEquals(50, oval.getWidth());
    assertEquals(50, oval.getHeight());

    // Execute the command with another new size
    resizeMod.execute("resize rect 20 20");
    resizeMod.execute("resize oval 20 20");

    // Check if the shape's size has been updated again
    assertEquals(20, rect.getWidth());
    assertEquals(20, rect.getHeight());
    assertEquals(20, oval.getWidth());
    assertEquals(20, oval.getHeight());
  }

  /**
   * Test that you can reassign the resize command to a different shape and resize the new shape.
   */
  @Test
  public void testReassign() {

    // Create new shapes and add them to a new list. Then reassign the resize command to the new list
    Rectangle rect2 = new Rectangle("rect2", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    Oval oval2 = new Oval("oval2", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    LinkedHashMap<String, IShape> shapes2 = new LinkedHashMap<String, IShape>();
    shapes2.put("rect2", rect2);
    shapes2.put("oval2", oval2);

    resizeMod = new ResizeMod(shapes2); // reassign to new list here
    resizeMod.execute("resize rect2 100 100");
    resizeMod.execute("resize oval2 100 100");

    // Check if the shape's size has been updated again
    assertEquals(100, rect2.getWidth());
    assertEquals(100, rect2.getHeight());
    assertEquals(100, oval2.getWidth());
    assertEquals(100, oval2.getHeight());
  }

  /**
   * Test that the command will not execute if the shape is not found.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testShapeNotFound() {
    resizeMod.execute("resize who? 50 50");
  }

  /**
   * Test that error will be thrown if the command does not have exactly 4 arguments.
   * String format should be "resize shapeName x y".
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidCommand() {
    resizeMod.execute("resize rect 50");
  }

  /**
   * Test error will be thrown if first word in the string is not "resize".
   */
  @Test (expected = IllegalArgumentException.class)
  public void testInvalidFirstWord() {
    resizeMod.execute("move rect 50 50");
  }

  /**
   * Test error will be thrown if numbers in the string are not integers/whole numbers.
   */
  @Test (expected = NumberFormatException.class)
  public void testInvalidNumber() {
    resizeMod.execute("resize rect 50.5 50");
  }

  /**
   * Test null String will throw an error.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNullString() {
    resizeMod.execute(null);
  }

  /**
   * Test empty String will throw an error.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testEmptyString() {
    resizeMod.execute("");
  }
}