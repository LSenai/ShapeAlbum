import controller.AlbumController;
import model.Model;


import java.io.IOException;
import java.util.Arrays;


public class PhotoAlbumMain {
  public static void main(String[] args) throws IOException {

    AlbumController controller = new AlbumController();
    Model model = new Model();

    String[] commandArgs = controller.readCommandLine(args);
    String inputFile = commandArgs[0];
    String viewType = commandArgs[2];
    System.out.println(Arrays.toString(commandArgs));

    controller.openAlbum(model, inputFile, viewType);

  }
}