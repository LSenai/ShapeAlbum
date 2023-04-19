import controller.AlbumController;
import model.Snapshot;
import org.junit.Test;
import view.WebView;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedHashMap;

import static org.junit.Assert.*;

public class WebViewTest {

  /**
   * Tests that the HTML file is written correctly by passing in a test case and comparing its html output to the
   * expected output of a known good file.
   */
  @Test
  public void testWriteHTMLFile() throws IOException {
    // read the expected, valid, HTML output file to a string
    String filePath = "TEST.html";
    byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
    String expected = new String(encodedBytes, StandardCharsets.UTF_8);

    // load up a model and controller with a test case
    model.Model model = new model.Model();
    AlbumController AlbumController = new controller.AlbumController();
    String[] args = {"buildings.txt", "dummy.html", "web", "800", "800"};
    AlbumController.openAlbum(model, args);
    LinkedHashMap<Snapshot, String> snapshots = model.getSnapshots();

    WebView webView = new WebView(snapshots, 800, 800, "dummy.html");

    assertEquals(expected, webView.generateHTML());

  }

  /**
   * Tests that the HTML file written above is equal to the expected output of a known good file.
   */
  @Test
  public void testGenerateHTML() throws IOException {
    // read the expected, valid, HTML output file to a string
    String filePath = "TEST.html";
    byte[] encodedBytes = Files.readAllBytes(Paths.get(filePath));
    String expected = new String(encodedBytes, StandardCharsets.UTF_8);


    filePath = "dummy.html";
    encodedBytes = Files.readAllBytes(Paths.get(filePath));
    String dummy = new String(encodedBytes, StandardCharsets.UTF_8);

    assertEquals(expected, dummy);
  }

}