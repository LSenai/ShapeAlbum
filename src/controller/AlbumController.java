package controller;

import model.IModel;
import model.Snapshot;
import model.shapes.IShape;
import view.GraphicalView;
import view.IView;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class AlbumController implements IAlbumController {

  private IModel model;
  private String viewType;

  @Override
  public void openAlbum(IModel model, String albumCommandsFile, String viewType) throws IOException {

    this.viewType = viewType;
    this.model = model;

    ArrayList<String> commands = this.readUserCommands(albumCommandsFile);
  /*  for (String command : commands) {
      System.out.println(command);
    }
    System.out.println(commands.size());
*/
    LinkedHashMap<String, IShape> shapes = model.getShapes();
    LinkedHashMap<Snapshot, String> snapshots = model.getSnapshots();

    for (String command : commands) {
      this.model.commandReceiver(command);
    }

    // System.out.println(model.printSnapshots());
    if (this.viewType.equalsIgnoreCase("graphical")) {
      GraphicalView view = new GraphicalView(model.getSnapshots());
      //view.display();
    }

  }

  /*
  Format:
  -in "name-of-command-file" -view "type-of-view" [-out "where-output-should-go"] [xmax] [ymax]
   */
  @Override
  public String[] readCommandLine(String[] args) {
    String inputFileName = null;
    String outputFileName = null;
    String viewType = null;
    int xMax = 1000;
    int yMax = 1000;

    // print each argument:
    for (String arg : args) {
      System.out.println(arg);
    }

    // Parse command line arguments
    for (int i = 0; i < args.length; i++) {
      if ("-in".equals(args[i])) {
//        System.out.println("Setting inputFileName to " + args[i + 1]);
        inputFileName = args[i + 1];
      } else if ("-out".equals(args[i])) {
//        System.out.println("Setting outputFileName to " + args[i + 1]);
        outputFileName = args[i + 1];
      } else if ("-view".equals(args[i]) || "-v".equals(args[i])) {
//        System.out.println("Found -view or -v flag");
//        System.out.println("Setting viewType to " + args[i + 1]);
        viewType = args[i + 1];
      } else if (args[i].matches("\\d+")) {
        if (i == args.length - 2) {
//          System.out.println("Setting xMax to " + args[i]);
          xMax = Integer.parseInt(args[i]);
        } else if (i == args.length - 1) {
//          System.out.println("Setting yMax to " + args[i]);
          yMax = Integer.parseInt(args[i]);
        }
      }
    }

    // Check for required arguments
    if (inputFileName == null || viewType == null) {
      System.err.println("Usage: java MyProgram -in \"name-of-command-file\" -view \"type-of-view\" " +
          "[-out \"where-output-should-go\"] [xmax] [ymax]");
      System.exit(1);
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

