package view;

import controller.IAlbumController;
import model.Snapshot;
import model.shapes.IShape;

import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;

/**
 * Webview for a static HTML/SVG view. This view will create a static HTML file that will display the snapshots.
 */
public class WebView implements IWebView {

  private final LinkedHashMap<Snapshot, String> snapshots;
  private final int WIDTH;
  private final int HEIGHT;
  private final String outputFileName;

  /**
   * Constructor for the WebView.
   *
   * @param snapshots the snapshots to be displayed
   * @param WIDTH     the width of the canvas
   * @param HEIGHT    the height of the canvas
   */
  public WebView(LinkedHashMap<Snapshot, String> snapshots, int WIDTH, int HEIGHT, String outputFileName) {
    this.snapshots = snapshots;
    this.WIDTH = WIDTH;
    this.HEIGHT = HEIGHT;
    if (outputFileName == null || outputFileName.isEmpty()) {
      throw new IllegalArgumentException("Output file name cannot be null or empty.");
    }
    this.outputFileName = outputFileName;

    if (snapshots.isEmpty()) {
      throw new IllegalArgumentException("Snapshots cannot be empty.");
    }
  }

  /**
   * Creates the SVG for the snapshot. Each snapshot will have a black frame around the border representing the canvas/
   * frame. Each shape will be represented by an SVG element, and then we'll return a string representation that
   * compiles all the shapes of the snapshot into one string.
   *
   * @param snapshot the snapshot to be converted to SVG
   * @return the SVG string representation of the snapshot
   */
  private String createSnapshotSVG(Snapshot snapshot) {

    if (snapshot == null) {
      throw new IllegalArgumentException("Snapshot cannot be null.");
    }

    LinkedHashMap<String, IShape> shapeList = snapshot.getShapes();
    StringBuilder snapshotSVGs = new StringBuilder();

    // Define the frame
    double frameX = this.WIDTH * 0.125;
    double frameY = this.HEIGHT * 0.125;
    double frameWidth = this.WIDTH * 0.75;
    double frameHeight = this.HEIGHT * 0.75;

    // Define the text
    String snapshotId = snapshot.getID();
    String snapshotDescription = snapshot.getDescription();
    double textX = frameX + frameWidth / 2;
    double textY = frameY - 20;
    snapshotSVGs.append("<text x=\"").append(textX).append("\" y=\"").append(textY)
        .append("\" font-size=\"20\" font-weight=\"bold\" text-anchor=\"middle\">")
        .append(snapshotId).append("</text>\n");
    if (snapshotDescription != null && !snapshotDescription.isEmpty()) {
      textY += 20;
      snapshotSVGs.append("<text x=\"").append(textX).append("\" y=\"").append(textY)
          .append("\" font-size=\"16\" text-anchor=\"middle\">")
          .append(snapshotDescription).append("</text>\n");
      // add some space below the description text
      textY += 10;
    }

    // Add the shape elements
    for (IShape shape : shapeList.values()) {
      double shapeY = frameY + shape.getLocation().getY();
      if (snapshotDescription != null && !snapshotDescription.isEmpty()) {
        // add some space for the description text
        shapeY += 15;
      }
      if (shape.getType().equals("Rectangle")) {
        snapshotSVGs.append("<rect x=\"").append(frameX + shape.getLocation().getX())
            .append("\" y=\"").append(shapeY)
            .append("\" width=\"").append(shape.getWidth())
            .append("\" height=\"").append(shape.getHeight())
            .append("\" fill=\"rgb(").append(shape.getColor().getR()).append(",")
            .append(shape.getColor().getG()).append(",").append(shape.getColor().getB())
            .append(")\" />\n");
      } else {
        // note that for an ellipse, the width and height are divided by two to get the radii
        snapshotSVGs.append("<ellipse cx=\"").append(frameX + shape.getLocation().getX())
            .append("\" cy=\"").append(shapeY)
            .append("\" rx=\"").append(shape.getWidth())
            .append("\" ry=\"").append(shape.getHeight())
            .append("\" fill=\"rgb(").append(shape.getColor().getR()).append(",")
            .append(shape.getColor().getG()).append(",").append(shape.getColor().getB())
            .append(")\" />\n");
      }
    }

    return snapshotSVGs.toString();
  }

  /**
   * Creates the HTML string that will display the snapshots. This method will create the HTML strings and then call the
   * createSnapshotSVG method to create the SVG for each snapshot.
   */
  public String generateHTML() {
    StringBuilder htmlBuilder = new StringBuilder();
    // add the HTML header
    htmlBuilder.append("<!DOCTYPE html>\n");
    htmlBuilder.append("<html>\n");
    htmlBuilder.append("<head>\n");
    htmlBuilder.append("<title>Shapes Album!</title>\n");
    htmlBuilder.append("<link rel=\"icon\" type=\"image/png\" href=\"shapes logo.png\">\n");
    htmlBuilder.append("</head>\n");
    htmlBuilder.append("<body>\n");

    // iterate through each snapshot and insert the SVG code
    for (Snapshot snapshot : this.snapshots.keySet()) {
      htmlBuilder.append("<svg width=\"").append(this.WIDTH).append("\" height=\"").append(this.HEIGHT).append("\">\n");
      htmlBuilder.append(this.createSnapshotSVG(snapshot));
      htmlBuilder.append("</svg>\n");
    }

    // add the HTML footer
    htmlBuilder.append("</body>\n");
    htmlBuilder.append("</html>");

    return htmlBuilder.toString();
  }

  /**
   * Writes the HTML string to a file.
   */
  public void writeHTMLFile() {
    try {
      FileWriter writer = new FileWriter(this.outputFileName);
      writer.write(this.generateHTML());
      writer.close();
    } catch (IOException e) {
      System.out.println("An error occurred while writing to the file.");
      e.printStackTrace();
    }
  }

}
