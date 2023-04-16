package controller;

import model.IModel;

import java.util.ArrayList;

public class AlbumController implements IAlbumController{

  private IModel model;



  @Override
  public void openAlbum(IModel model) {

  }

  @Override
  public String[] readCommandLine(String[] args) {
    String inputFileName = null;
    String outputFileName = null;
    String viewType = null;
    int xMax = 1000;
    int yMax = 1000;

    // Parse command line arguments
    for (int i = 0; i < args.length; i++) {
      if ("-in".equals(args[i])) {
        if (i < args.length - 1) {
          inputFileName = args[i + 1];
          i++;
        }
      } else if ("-out".equals(args[i])) {
        if (i < args.length - 1) {
          outputFileName = args[i + 1];
          i++;
        }
      } else if ("-view".equals(args[i]) || "-v".equals(args[i])) {
        if (i < args.length - 1) {
          viewType = args[i + 1];
          i++;
        }
      } else if (args[i].matches("\\d+")) {
        if (i == args.length - 2) {
          xMax = Integer.parseInt(args[i]);
        } else if (i == args.length - 1) {
          yMax = Integer.parseInt(args[i]);
        }
      }
    }

    // Check for required arguments
    if (inputFileName == null || viewType == null) {
      System.err.println("Usage: java MyProgram -in \"name-of-command-file\" -view \"type-of-view\" [-out \"where-output-should-go\"] [xmax] [ymax]");
      System.exit(1);
    }

    // Return parsed arguments as string array
    String[] parsedArgs = {inputFileName, outputFileName, viewType, String.valueOf(xMax), String.valueOf(yMax)};
    return parsedArgs;
  }

  @Override
  public ArrayList<String> readUserCommands() {
    return null;
  }
}
