package model.Commands;

import model.Commands.RemoveShapeCommand;
import org.junit.Before;
import org.junit.Test;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class RemoveShapeCommandTest {
  private IShape rect;
  private IShape oval;
  private LinkedHashMap<String, IShape> shapes;
  private RemoveShapeCommand removeShape;


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

    removeShape = new RemoveShapeCommand(shapes);
  }

  /**
   * Test the execute method of the RemoveShapeCommand class.
   */
  @Test
  public void testExecute() {
    // test to see the command works
    removeShape.execute("remove rect");
    removeShape.execute("remove oval");

    // Check if the shape has been removed
    assertFalse(shapes.containsKey("rect"));
    assertFalse(shapes.containsKey("oval"));
  }

  /**
   * Test that you can reassign the remove command to a different shape list and execute the command.
   */
  @Test
  public void testExecute2() {
    // test to see the command works
    removeShape.execute("remove rect");
    removeShape.execute("remove oval");

    // Check if the shape has been removed
    assertFalse(shapes.containsKey("rect"));
    assertFalse(shapes.containsKey("oval"));

    // Create a new shape list and reassign the remove command to the new list
    Oval newOval = new Oval("newOval", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    LinkedHashMap<String, IShape> newList = new LinkedHashMap<>();
    newList.put("newOval", newOval);
    assertTrue(newList.containsKey("newOval"));

    removeShape = new RemoveShapeCommand(newList); // reassign the command to a new list
    removeShape.execute("remove newOval"); // invoke the command to remove the target shape from the new list
    assertFalse(newList.containsKey("newOval"));
  }

  /**
   * Test exception is thrown when the shape is not in the list of shapes.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNoShapeInList() {
    assertTrue(shapes.containsKey("rect"));
    removeShape.execute("remove rect");
    assertFalse(shapes.containsKey("rect")); // remove rect again. list should be an empty. Error should be raised
    removeShape.execute("remove rect");
  }

  /**
   * Test null command String.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullCommand() {
    removeShape.execute(null);
  }

  /**
   * Test empty command String.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyCommand() {
    removeShape.execute("");
  }

  /**
   * Test invalid command type. Method should only accept "remove" as the first word. Anything else is invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandType() {
    removeShape.execute("move rect 10 10");
  }

  /**
   * Test invalid number of args/commands in string. Must be exactly two words.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNumArgs() {
    removeShape.execute("remove");
  }

}