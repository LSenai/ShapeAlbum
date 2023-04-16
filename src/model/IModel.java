package model;

import model.shapes.IShape;

import java.util.LinkedHashMap;

public interface IModel {



  /**
   * Method acts as the receiver for all behavioral commands. It parses the command and delegates it to the appropriate
   * command object.
   * @param command the command to parse and execute
   */
  void commandReceiver(String command);

  /**
   * Get the shapes list.
   * @return the shapes list
   */
  LinkedHashMap<String, IShape> getShapes();

  /**
   * Method to print the shapes list.
   * @return the shapes list as a string
   */
  String printShapes();

  /**
   * Method to print the snapshots list.
   * @return the snapshots list as a string
   */
  String printSnapshots();

  /**
   * Get the snapshots list.
   * @return the snapshots list
   */
  public LinkedHashMap<Snapshot, String> getSnapshots();

  /**
   * Method to clear the shapes and snapshots lists.
   */
  void clear();

}
