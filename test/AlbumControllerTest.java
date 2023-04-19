import controller.AlbumController;
import model.Model;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class AlbumControllerTest {

  Model model;
  private AlbumController albumController;

  @Before
  public void setUp() {
    albumController = new AlbumController();
    model = new Model();
  }

  /**
   * Test openAlbum method with valid input. This test demonstrates that the controller properly links with the model
   * and should also briefly initialize the graphical view window, showing that it is executing the view's
   * display method.
   */
  @Test
  public void testOpenAlbumGraphical() throws IOException {
    String[] args = {"buildings.txt", null, "graphical", "800", "800"};
    albumController.openAlbum(model, args);
    assertEquals(3, model.getSnapshots().size());
  }

  /**
   * Test openAlbum method with valid input. This test demonstrates that the controller properly links with the model
   * and should also initialize the web view, showing that it is executing the view's writeHTMLFile method.
   * A file called TESTOPEN.html should be created in the project root, which I have manually deleted after each test run.
   */
  @Test
  public void testOpenAlbumWeb() throws IOException {
    String[] args = {"buildings.txt", "TESTOPEN.html", "web", "800", "800"};
    albumController.openAlbum(model, args);
    assertEquals(3, model.getSnapshots().size());
  }


  /**
   * Test openAlbum method with invalid input - null command args string.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testNullCommandArgs() throws IOException {
    albumController.openAlbum(model, null);
  }

  /**
   * Test readCommandLine method with valid input.
   */
  @Test
  public void testValidInput() {
    String[] args = {"-in", "buildings.txt", "-v", "graphical", "800", "800"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);

    assertEquals("buildings.txt", result[0]);
    assertEquals(null, result[1]);
    assertEquals("graphical", result[2]);
    assertEquals("800", result[3]);
    assertEquals("800", result[4]);

  }

  /**
   * Test an illegal argument exception since we are missing the input file name.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMissingInput() {
    String[] args = {"-v", "web", "-out", "myWeb.html"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);
  }

  /**
   * Should throw an illegal argument exception since we are missing the view.
   */
  @Test (expected = IllegalArgumentException.class)
  public void testMissingView() {
    String[] args = {"-in", "buildings.txt"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);
  }

  /**
   * Should throw an index out of bounds exception since we are missing the output file name.
   */
  @Test (expected = IndexOutOfBoundsException.class)
  public void testInvalidInput() {
    String[] args = {"-in", "buildings.txt", "-v", "web", "-out"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);
  }

  /**
   * Test readUserCommands method with valid input on the model.
   */
  @org.junit.Test
  public void readUserCommands() throws IOException {
    String file = "demo_input.txt";
    AlbumController controller = new AlbumController();
    ArrayList<String> commands = controller.readUserCommands(file);
    String firstCommand = "shape myrect rectangle 200 200 50 100 255 0 0";
    assertEquals(firstCommand, commands.get(0));

    String lastCommand = "snapshot Selfie after removing the rectangle from the picture";
    assertEquals(lastCommand, commands.get(commands.size() - 1));
  }

  /**
   * Test readUserCommands method with invalid input on the model.
   *
   * @throws IOException  if the file is not found
   */
  @Test (expected = IOException.class)
  public void testInvalidFile() throws IOException {
    String file = "invalid_file.txt";
    AlbumController controller = new AlbumController();
    ArrayList<String> commands = controller.readUserCommands(file);
  }
}