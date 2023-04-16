package controller;

import model.IModel;

import java.io.IOException;
import java.util.ArrayList;

public interface IAlbumController {

  void openAlbum(IModel model, String albumCommandsFile) throws IOException;

  /**
   * Read command line arguments, parse them, and return them as a string array.
   * @param args command line arguments
   * @return string array of parsed command line arguments
   */
  String[] readCommandLine(String[] args);

  ArrayList<String> readUserCommands(String fileName) throws IOException;

}
