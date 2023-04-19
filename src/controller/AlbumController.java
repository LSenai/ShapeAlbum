package controller;

import model.IModel;

import view.GraphicalView;
import view.WebView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class AlbumController implements IAlbumController {

  private IModel model;
  String inputFile;
  String outputFile;
  private String viewType;
  int height;
  int width;

  @Override
  public void openAlbum(IModel model, String[] commandArgs) throws IOException {

    if (commandArgs == null) throw new IllegalArgumentException("There are no commands to parse");
    // assign parsed args to fields
    this.inputFile = commandArgs[0];
    this.outputFile = commandArgs[1]; // could be null
    this.viewType = commandArgs[2];
    this.width = Integer.parseInt(commandArgs[3]);
    this.height = Integer.parseInt(commandArgs[4]);

    this.model = model;

    ArrayList<String> commands = this.readUserCommands(inputFile);

    // invoke all .txt files commands on model
    for (String command : commands) {
      this.model.commandReceiver(command);
    }

    // System.out.println(model.printSnapshots());
    if (this.viewType.equalsIgnoreCase("graphical")) {
      GraphicalView view = new GraphicalView(model.getSnapshots(), width, height);
      view.display();
    }
    else if (this.viewType.equalsIgnoreCase("web")) {
      WebView view = new WebView(model.getSnapshots(), width, height, outputFile);
      view.writeHTMLFile();
    }

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
        if (args[i +1] == null) throw new IndexOutOfBoundsException("input file name cannot be null");
        else inputFileName = args[i + 1];
      } else if ("-out".equals(args[i])) {
        if (args[i +1] == null) throw new IndexOutOfBoundsException("output file name cannot be null");
          else outputFileName = args[i + 1];
      } else if ("-view".equals(args[i]) || "-v".equals(args[i])) {
        if (args[i +1] != null) viewType = args[i + 1];
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
      throw new IllegalArgumentException("input file and view type cannot be null");
    }

    if (!viewType.equalsIgnoreCase("web") && !viewType.equalsIgnoreCase("graphical")) {
      throw new IllegalArgumentException("view type must be either web or graphical");
    }

    // Return parsed arguments as string array
    String[] parsedArgs = {inputFileName, outputFileName, viewType, String.valueOf(xMax), String.valueOf(yMax)};
    return parsedArgs;
  }

  @Override
  public ArrayList<String> readUserCommands(String fileName) throws IOException {
    ArrayList<String> commands = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line = reader.readLine();
      while (line != null) {
        // Remove leading white spaces and replace multiple spaces with a single space
        String cleanedLine = line.trim().replaceAll("\\s+", " ");
        if (!cleanedLine.startsWith("#") && !cleanedLine.isEmpty()) {
          commands.add(cleanedLine);
        }
        line = reader.readLine();
      }
    } catch (IOException e) {
      System.err.println("Error reading file: " + e.getMessage());
      throw e;
    }
    return commands;
  }
}

