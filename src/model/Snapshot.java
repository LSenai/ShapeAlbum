package model;

import model.shapes.IShape;

import java.sql.Timestamp;
import java.util.LinkedHashMap;

/**
 * A snapshot of the model (canvas) at a given time. A snapshot saves a copy of the shapes list when it is invoked.
 */
public class Snapshot {
  private static int nextID = 1; // increments with each new snapshot. Prevents duplicate IDs from hash collisions.
  private int ID;
  private Timestamp timestamp;
  private String description;
  private LinkedHashMap<String, IShape> shapes;
  private static final int ID_OFFSET = 10031;

  /**
   * Constructs a snapshot of the model at a given time.
   * @param shapes the shapes list of the model.
   * @param description the description of the snapshot.
   */
  public Snapshot(LinkedHashMap<String, IShape> shapes, String description) {

    this.shapes = shapes;
    this.timestamp = new Timestamp(System.currentTimeMillis());
    this.description = description;
    this.ID = this.timestamp.hashCode() % ID_OFFSET;
    nextID++;
  }

  /**
   * Override the equals method to compare snapshots by their attributes.
   * @return true if the snapshots are equal, false otherwise.
   */
  @Override
  public boolean equals(Object obj) {
    if (obj == null) {
      return false;
    }
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof Snapshot)) {
      return false;
    }
    Snapshot other = (Snapshot) obj;
    return this.ID == other.ID && this.timestamp.equals(other.timestamp)
        && this.description.equals(other.description) && this.shapes.equals(other.shapes); // recursively compare shapes
  }

  @Override
  public String toString() {
    // iterate through the shapes list and add to string builder
    StringBuilder sb = new StringBuilder();
    for (IShape shape : shapes.values()) {
      sb.append(shape.toString() + "\n");
    }
    return "Snapshot ID: " + this.ID + "\n" + "Timestamp: " + this.timestamp + "\n" + "Description: "
        + this.description + "\n" + "Shape Information: " + "\n" + sb.toString();
  }

  /**
   * Returns the description of the snapshot as a String.
   * @return the description of the snapshot.
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * Returns the shapes list of the snapshot.
   * @return the shapes list of the snapshot.
   */
  public LinkedHashMap<String, IShape> getShapes() {
    return this.shapes;
  }

  /**
   * Returns the ID of the snapshot.
   * @return the ID of the snapshot.
   */
  public int getID() {
    return this.ID;
  }

  /**
   * Returns the timestamp of the snapshot.
   * @return the timestamp of the snapshot.
   */
  public Timestamp getTimestamp() {
    return this.timestamp;
  }
}
