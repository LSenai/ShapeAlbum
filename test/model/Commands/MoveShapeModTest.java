package model.Commands;

import static org.junit.Assert.assertEquals;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import model.Commands.MoveShapeMod;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;
import org.junit.Before;
import org.junit.Test;

/**
 * Test class for the MoveShapeMod class.
 */
public class MoveShapeModTest {

  private IShape rect;
  private IShape oval;
  private LinkedHashMap<String, IShape> shapes;
  private MoveShapeMod moveShape;

  /**
   * Set up the test environment.
   */
  @Before
  public void setUp() {
    rect = new Rectangle("rect", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    oval = new Oval("oval", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    shapes = new LinkedHashMap<>();
    shapes.put("rect", rect);
    shapes.put("oval", oval);

    moveShape = new MoveShapeMod(shapes);
  }

  /**
   * Test the execute method of the MoveShapeMod class.
   */
  @Test
  public void testExecute() {
    // test to see the command works
    moveShape.execute("move rect 10 10");
    moveShape.execute("move oval 10 10");

    // Check if the shape's location has been updated
    assertEquals(new Point2D.Double(10, 10), rect.getLocation());
    assertEquals(new Point2D.Double(10, 10), oval.getLocation());

    // Execute the command with another new location
    moveShape.execute("move rect 20 20");
    moveShape.execute("move oval 20 20");

    // Check if the shape's location has been updated again
    assertEquals(new Point2D.Double(20, 20), rect.getLocation());
    assertEquals(new Point2D.Double(20, 20), oval.getLocation());
  }

  /**
   * Test that you can reassign the move command to another list of shapes.
   */
  @Test
  public void testExecuteWithDifferentShape() {

    // create a new shape and shape list to reassign the command to
    IShape newRect = new Rectangle("newRect", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    LinkedHashMap<String, IShape> newList = new LinkedHashMap<>();
    newList.put("newRect", newRect);


    // Reassign the move command to a different shape (oval)
    moveShape = new MoveShapeMod(newList);

    // Execute the command with another new location
    moveShape.execute("move newRect 100 100");

    // Check if the shape's location has been updated again
    assertEquals(new Point2D.Double(100, 100), newRect.getLocation());
  }

  /**
   * Test the execute method of the MoveShapeMod class with invalid location.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithInvalidLocation() {
    moveShape.execute("move rect -1 0");
  }

  /**
   * Test the execute method of the MoveShapeMod class with invalid number of commands. If the String does not contain
   * 4 words, then it is considered invalid. Syntax should be: "Move shape x y".
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithInvalidNumberOfCommands() {
    moveShape.execute("move rect 10");
  }

  /**
   * Test the execute method of the MoveShapeMod class with invalid shape name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithInvalidShapeName() {
    moveShape.execute("move who? 10 10");
  }

  /**
   * Test the execute method of the MoveShapeMod class with a non integer.
   */
  @Test(expected = NumberFormatException.class)
  public void testExecuteWithNonInteger() {
    moveShape.execute("move rect 10 10.5");
  }

  /**
   * Tests null String for the execute method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithNullString() {
    moveShape.execute(null);
  }

  /**
   * Tests empty String for the execute method.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithEmptyString() {
    moveShape.execute("");
  }
}