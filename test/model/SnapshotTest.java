package model;

import org.junit.Before;
import org.junit.Test;
import model.shapes.Color;
import model.shapes.IShape;
import model.shapes.Oval;
import model.shapes.Rectangle;

import java.awt.geom.Point2D;
import java.sql.Timestamp;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Test class for the Snapshot class.
 */
public class SnapshotTest {
  private Snapshot snapshot;
  private LinkedHashMap<String, IShape> shapes;
  private IShape rect;
  private IShape oval;

  /**
   * Set up the test environment.
   */
  @Before
  public void setUp() {
    shapes = new LinkedHashMap<>();
    rect = new Rectangle("rect", 10, 10, new Point2D.Double(0, 0),
                          new Color(0, 0, 0));
    oval = new Oval("oval", 10, 10, new Point2D.Double(0, 0),
                    new Color(0, 0, 0));
    shapes.put("R", rect);
    shapes.put("O", oval);
  }

  /**
   * Test the constructor of the Snapshot class. Check the class generally works. There is a separate test to check
   * the snapshot command is compatible with this snapshot class.
   */
  @Test
  public void testSnapshot() {
    snapshot = new Snapshot(shapes, "test");
    assertEquals("test", snapshot.getDescription());
    assertEquals(2, snapshot.getShapes().size()); // test that we have two shapes in our snapshot
  }

  /**
   * Test the toString method of the Snapshot class. Was tricky given the timestamp is constantly changing, so I
   * chose to extract it and the ID.
   */
  @Test
  public void testToString() {
    snapshot = new Snapshot(shapes, "test");
    int ID = snapshot.getID();
    Timestamp timestamp = snapshot.getTimestamp();
    String description = snapshot.getDescription();
    String shapesString = "Name: rect\n" + "Type: Rectangle\nMin corner: (0.0, 0.0), Width: 10, Height: 10, " +
        "Color: (0, 0, 0)\n" + "Name: oval\n" + "Type: Oval\n" +
        "Min corner: (0.0, 0.0), X Radius: 10, Y Radius: 10, Color: (0, 0, 0)\n";
    String expected = "Snapshot ID: " + ID + "\n" + "Timestamp: " + timestamp + "\n" + "Description: "
        + description + "\n" + "Shape Information: " + "\n" + shapesString;

    assertEquals(expected, snapshot.toString());
  }

  /**
   * Test the getDescription method.
   */
  @Test
  public void getDescription() {
    snapshot = new Snapshot(shapes, "test");
    assertEquals("test", snapshot.getDescription());
  }

  /**
   * Test the equals method.
   */
  @Test
  public void testEquals() {
    snapshot = new Snapshot(shapes, "test");
    Snapshot snapshot2 = new Snapshot(shapes, "test");

    assertEquals(snapshot, snapshot2);

    Rectangle newRect = new Rectangle("newRect", 10, 10, new Point2D.Double(0, 0),
        new Color(0, 0, 0));
    LinkedHashMap<String, IShape> newShapes = new LinkedHashMap<>();
    newShapes.put("R", newRect);
    snapshot2 = new Snapshot(newShapes, "test");
    assertNotEquals(snapshot, snapshot2);
  }
}