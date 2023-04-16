package model;

import org.junit.Before;
import org.junit.Test;
import model.shapes.Color;
import model.shapes.IShape;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Test class for the Model class.
 */
public class ModelTest {

  private Model m;

  /**
   * Set up the test environment.
   */
  @Before
  public void setUp() throws Exception {
    m = new Model();
    LinkedHashMap<String, IShape> shapes = m.getShapes();
    LinkedHashMap<Snapshot, String> snapshots = m.getSnapshots();

  }

  /**
   * Test the constructor of the Model class instantiates the shapes and snapshots correctly.
   */
  @Test
  public void testModel() {
    assertEquals(0, m.getShapes().size());
    assertEquals(0, m.getSnapshots().size());
  }

  /**
   * Test adding a shape to the model. Command is "shape name type x-coor y-coor width height r g b".
   */
  @Test
  public void testCreateShape() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    assertEquals(1, m.getShapes().size());
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");
    assertEquals(2, m.getShapes().size());
  }

  /**
   * Test removing a shape. Command is "remove shapeName".
   */
  @Test
  public void testRemoveShape() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    assertEquals(1, m.getShapes().size());
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");
    assertEquals(2, m.getShapes().size());

    // now remove the shapes
    m.commandReceiver("remove rect1");
    assertEquals(1, m.getShapes().size());
    m.commandReceiver("remove oval1");
    assertEquals(0, m.getShapes().size());
  }

  /**
   * Test moving a shape. Command is "move shapeName x y".
   */
  @Test
  public void testMoveShape() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");

    // now move the shapes
    m.commandReceiver("move rect1 10 10");
    m.commandReceiver("move oval1 10 10");
    Point2D.Double expected = new Point2D.Double(10, 10);
    assertTrue(m.getShapes().get("rect1").getLocation().equals(expected));
    assertTrue(m.getShapes().get("oval1").getLocation().equals(expected));

    // test we can move the shape again
    expected = new Point2D.Double(20, 20);
    m.commandReceiver("move oval1 20 20");
    m.commandReceiver("move rect1 20 20");
    assertTrue(m.getShapes().get("oval1").getLocation().equals(expected));
    assertTrue(m.getShapes().get("rect1").getLocation().equals(expected));
  }


  /**
   * Test changing the color of a shape. Command is "color shapeName r g b".
   */
  @Test
  public void testChangeColor() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");

    // now change the color of the shapes
    Color expected = new Color(255, 255, 255);

    m.commandReceiver("color rect1 255 255 255");
    m.commandReceiver("color oval1 255 255 255");

    assertTrue(m.getShapes().get("rect1").getColor().equals(expected));
    assertTrue(m.getShapes().get("oval1").getColor().equals(expected));

    // test we can change the color again
    expected = new Color(150, 0, 0);
    m.commandReceiver("color oval1 150 0 0");
    m.commandReceiver("color rect1 150 0 0");
    assertTrue(m.getShapes().get("oval1").getColor().equals(expected));
    assertTrue(m.getShapes().get("rect1").getColor().equals(expected));
  }

  /**
   * Test resizing a shape. Command is "resize shapeName width height".
   */
  @Test
  public void testResizeShape() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");

    // now resize the shapes
    m.commandReceiver("resize rect1 20 20");
    m.commandReceiver("resize oval1 20 20");
    int expectedWidth = 20;
    int expectedHeight = 20;
    assertTrue(m.getShapes().get("rect1").getWidth() == expectedWidth);
    assertTrue(m.getShapes().get("oval1").getHeight() == expectedHeight);

    // test we can resize the shape again
    expectedWidth = 100; // only change width
    m.commandReceiver("resize oval1 100 20");
    m.commandReceiver("resize rect1 100 20");
    assertTrue(m.getShapes().get("oval1").getWidth() == expectedWidth);
    assertTrue(m.getShapes().get("rect1").getWidth() == expectedWidth);
    // check that height is not changed (should still be 20)
    assertTrue(m.getShapes().get("oval1").getHeight() == expectedHeight);
    assertTrue(m.getShapes().get("rect1").getHeight() == expectedHeight);
  }


  /**
   * Test illegal invalid command input raises an exception. Specifically the first word of the command.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommand() {
    m.commandReceiver("invalidCommand");
  }


  /**
   * Tests a null command raises an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullCommand() {
    m.commandReceiver(null);
  }

  /**
   * Tests an empty command raises an exception.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyCommand() {
    m.commandReceiver("");
  }

  /**
   * Test error is raised if inputting a valid command with invalid arguments
   * For example: first word is valid, but has no other args, i.e. "move" with nothing else.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidArgs() {
    m.commandReceiver("move");
  }

  /**
   * Test error is raised if inputting a valid command with invalid arguments.
   * For example: first word is valid, but has too many args, i.e. "move" with 3 args.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testTooManyArgs() {
    m.commandReceiver("move rect1 10 10 10");
  }

  /**
   * Test error is raised with an invalid command down the chain. For example "move" is valid, but
   * moving a shape that doesn't exist in our list is not valid.
   * Tests that errors in the command objects are being raised correctly.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCommandDownChain() {
    m.commandReceiver("move rect1 10 10"); // rect1 hasn't been added
  }

  /**
   * Test getSnapshots method.
   */
  @Test
  public void getSnapshots() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");
    assertTrue(m.getSnapshots().size() == 0);

    m.commandReceiver("snapshot first snap");
    assertTrue(m.getSnapshots().size() == 1);

    // move a shape
    m.commandReceiver("move rect1 100 100");

    m.commandReceiver("snapshot second snap");
    assertTrue(m.getSnapshots().size() == 2);
  }

  /**
   * Test printShapes method.
   * String format for each shape should be:
   *    * Name: R
   *    * Type: Rectangle
   *    * Min corner: (200.0,200.0), Width: 50, Height: 100, Color: (1, 0, 0)
   */
  @Test
  public void printShapes() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");

    String expected = "Name: rect1\n"
        + "Type: Rectangle\n"
        + "Min corner: (5.0, 5.0), Width: 10, Height: 10, Color: (0, 0, 0)\n"
        + "Name: oval1\n"
        + "Type: Oval\n"
        + "Min corner: (5.0, 5.0), X Radius: 10, Y Radius: 10, Color: (0, 0, 0)\n";

    assertEquals(expected, m.printShapes());
  }

  /**
   * Test printSnapshots method.
   */
  @Test
  public void printSnapshots() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");

    m.commandReceiver("snapshot first snap");

    // access contents of the first snapshot
    Map.Entry<Snapshot, String> lastSnap = null;
    for (Map.Entry<Snapshot, String> entry : m.getSnapshots().entrySet()) {
      lastSnap = entry;
    }
    Snapshot s = lastSnap.getKey();
    int ID = s.getID();
    Timestamp timestamp = s.getTimestamp();
    String description = s.getDescription(); // could also use lastSnap.getValue()
    String snap1ShapesString = "Name: rect1\n"
        + "Type: Rectangle\n"
        + "Min corner: (5.0, 5.0), Width: 10, Height: 10, Color: (0, 0, 0)\n"
        + "Name: oval1\n"
        + "Type: Oval\n"
        + "Min corner: (5.0, 5.0), X Radius: 10, Y Radius: 10, Color: (0, 0, 0)\n";

    String expected = "Snapshot ID: " + ID + "\n"
        + "Timestamp: " + timestamp + "\n"
        + "Description: " + description + "\n"
        + "Shape Information: \n" + snap1ShapesString + "\n";

    assertEquals(expected, m.printSnapshots());

    // move a shape
    m.commandReceiver("move rect1 100 100");
    m.commandReceiver("snapshot second snap");

    // access contents of the second snapshot
    lastSnap = null;
    for (Map.Entry<Snapshot, String> entry : m.getSnapshots().entrySet()) {
      lastSnap = entry;
    }
    s = lastSnap.getKey();
    int ID2 = s.getID();
    Timestamp timestamp2 = s.getTimestamp();
    String description2 = s.getDescription(); // could also use lastSnap.getValue()
    String snap2ShapesString = "Name: rect1\n"
        + "Type: Rectangle\n"
        + "Min corner: (100.0, 100.0), Width: 10, Height: 10, Color: (0, 0, 0)\n"
        + "Name: oval1\n"
        + "Type: Oval\n"
        + "Min corner: (5.0, 5.0), X Radius: 10, Y Radius: 10, Color: (0, 0, 0)\n";

    String expected2 = "Snapshot ID: " + ID2 + "\n"
        + "Timestamp: " + timestamp2 + "\n"
        + "Description: " + description2 + "\n"
        + "Shape Information: \n" + snap2ShapesString + "\n";

    assertEquals(expected + expected2, m.printSnapshots());
  }

  /**
   * Test clear method.
   */
  @Test
  public void clear() {
    m.commandReceiver("shape rect1 rectangle 5 5 10 10 0 0 0");
    m.commandReceiver("shape oval1 oval 5 5 10 10 0 0 0");
    m.commandReceiver("snapshot first snap");
    m.commandReceiver("move rect1 100 100");
    m.commandReceiver("snapshot second snap");
    assertTrue(m.getSnapshots().size() == 2);
    assertTrue(m.getShapes().size() == 2);
    m.clear();
    assertEquals("", m.printShapes());
    assertEquals("", m.printSnapshots());
    assertTrue(m.getSnapshots().size() == 0);
    assertTrue(m.getShapes().size() == 0);
  }
}