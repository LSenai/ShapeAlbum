package model.Commands;

import model.Snapshot;
import model.shapes.IShape;

import java.util.LinkedHashMap;

/**
 * Class to take a snapshot of the current state of the shapes list. Follows the Command design pattern.
 */
public class TakeSnapshotCommand implements Command {

  private LinkedHashMap<String, IShape> shapes;
  private LinkedHashMap<Snapshot, String> snapshots;

  /**
   * Constructor for the TakeSnapshotCommand class.
   * @param shapes the list of shapes
   * @param snapshots the list of snapshots
   */
  public TakeSnapshotCommand(LinkedHashMap<String, IShape> shapes, LinkedHashMap<Snapshot, String> snapshots) {
    this.shapes = shapes;
    this.snapshots = snapshots;
  }

  /**
   * Method to copy the shapes list.
   * @return a copy of the shapes list
   */
  private LinkedHashMap<String, IShape> copyShapes() {
    LinkedHashMap<String, IShape> copy = new LinkedHashMap<>();
    for (String key : shapes.keySet()) {
      copy.put(key, shapes.get(key).copy());
    }
    return copy;
  }

  /**
   * Executes the command to take a snapshot of the current state of the shapes list.
   * The first word of the command is "snapshot", and the rest of the string is an OPTIONAL description of the snapshot.
   * @param command the command to take a snapshot
   */
  @Override
  public void execute(String command) {
    Util.validateString(command); // check for null or empty string

    // Splits the input string at the first space character, or at most 2 times. The idea is that the first word is the
    // command ("snapshot"), and the rest of the string is the OPTIONAL description of the snapshot.

    String[] parts = command.split("\\s+", 2);

    checkExceptions(parts);

    // Save the rest of the string, if there is any, as a second element
    String snapDescription = (parts.length > 1) ? parts[1] : "";

    // deep copy of the shapes list
    LinkedHashMap<String, IShape> shapesCopy = copyShapes();

    Snapshot snapshot = new Snapshot(shapesCopy, snapDescription);
    this.snapshots.put(snapshot, snapshot.getDescription());
  }

  private void checkExceptions(String[] parts) throws IllegalArgumentException {
    if (!parts[0].equalsIgnoreCase("snapshot")) {
      throw new IllegalArgumentException("Invalid command. Not a snapshot command");
    }
  }

}
