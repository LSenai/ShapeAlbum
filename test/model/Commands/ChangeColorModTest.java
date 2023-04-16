import model.Commands.ChangeColorMod;

import org.junit.Before;
import org.junit.Test;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Rectangle;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Test class for the ChangeColorMod class.
 */
public class ChangeColorModTest {
  private LinkedHashMap<String, IShape> shapes;
  private IShape shape;
  private ChangeColorMod changeColor;

  /**
   * Set up the test environment.
   */
  @Before
  public void setUp() {
    shape = new Rectangle("rect", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    shapes = new LinkedHashMap<>();
    shapes.put("rect", shape);
    changeColor = new ChangeColorMod(shapes);
  }
  /**
   * Test the execute method of the ChangeColorMod class.
   */
  @Test
  public void testExecute() {
    // Execute the command with new color (red)
    changeColor.execute("Color rect 255 0 0");

    // Check if the shape's color has been updated
    assertEquals(new Color(255, 0, 0), shape.getColor());

    // Execute the command with another new color (green)
    changeColor.execute("Color rect 0 255 0");

    // Check if the shape's color has been updated again
    assertEquals(new Color(0, 255, 0), shape.getColor());
  }

  /**
   * Test the execute method of the ChangeColorMod class with invalid color.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithInvalidColor() {
    changeColor.execute("Color rect 255 0 -1");
  }

  /**
   * Test the execute method of the ChangeColorMod class with invalid number of commands. If the String does not contain
   * 5 words, then it is considered invalid.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithInvalidCommandNumber() {
    changeColor.execute("Color rect");
  }

  /**
   * Test the execute method of the ChangeColorMod class with invalid shape name. The second word in the command string
   * must match the shape's name.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithUnknownShape() {
    changeColor.execute("Color unknown 255 0 0");
  }

  /**
   * Tests that a non-Integer value for RGB values will throw a NumberFormatException.
   */
  @Test(expected = NumberFormatException.class)
  public void testExecuteWithInvalidRGB() {
    changeColor.execute("Color rect 255 0 0.5");
  }

  /**
   * Tests that a null command String will throw an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithNullCommand() {
    changeColor.execute(null);
  }

  /**
   * Test that an empty command String will throw an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testExecuteWithEmptyCommand() {
    changeColor.execute("");
  }
}
