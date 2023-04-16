package controller;

import org.junit.Test;

import static org.junit.Assert.*;

public class AlbumControllerTest {

  @org.junit.Before
  public void setUp() throws Exception {
  }

  @org.junit.Test
  public void openAlbum() {
  }


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

  @Test
  public void testMissingInput() {
    String[] args = {"-v", "web", "-out", "myWeb.html"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);
    assertNull(result);
  }

  @Test (expected = Error.class)
  public void testMissingView() {
    String[] args = {"-in", "buildings.txt"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);
  }

  @Test (expected = Error.class)
  public void testInvalidInput() {
    String[] args = {"-in", "buildings.txt", "-v", "web", "-out"};
    AlbumController controller = new AlbumController();
    String[] result = controller.readCommandLine(args);
  }

  @org.junit.Test
  public void readUserCommands() {
  }
}