package model.Commands;

import model.Commands.TakeSnapshotCommand;
import model.Snapshot;
import model.shapes.IShape;
import model.shapes.Oval;
import org.junit.Before;
import org.junit.Test;
import model.shapes.Color;
import model.shapes.Rectangle;

import java.awt.geom.Point2D;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

/**
 * Test class for the TakeSnapshotCommand class.
 */
public class TakeSnapshotCommandTest {

  private IShape rect;
  private IShape oval;
  private LinkedHashMap<String, IShape> shapes;
  private LinkedHashMap<Snapshot, String> snapshots;

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
    snapshots = new LinkedHashMap<>();
  }

  /**
   * Test the execute method of the TakeSnapshotCommand class.
   */
  @Test
  public void testExecute() {
    // test to see the command works
    TakeSnapshotCommand takeSnapshotCommand = new TakeSnapshotCommand(shapes, snapshots);
    takeSnapshotCommand.execute("Snapshot first snapshot");

    // Check if the snapshot has been added to the snapshots list
    assertEquals(1, snapshots.size());

    // Execute the command again. This time without a description
    takeSnapshotCommand.execute("Snapshot");

    // Check if the snapshot has been added to the snapshots list again
    assertEquals(2, snapshots.size());
  }

  /**
   * Test that a null command string throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNullCommand() {
    TakeSnapshotCommand takeSnapshotCommand = new TakeSnapshotCommand(shapes, snapshots);
    takeSnapshotCommand.execute(null);
  }

  /**
   * Test that an empty command string throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testEmptyCommand() {
    TakeSnapshotCommand takeSnapshotCommand = new TakeSnapshotCommand(shapes, snapshots);
    takeSnapshotCommand.execute("");
  }
}