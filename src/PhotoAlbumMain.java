import controller.AlbumController;
import model.Model;


import java.io.IOException;


public class PhotoAlbumMain {
  public static void main(String[] args) throws IOException {

    AlbumController controller = new AlbumController();
    Model model = new Model();

    String[] commandArgs = controller.readCommandLine(args); // validates the args

    controller.openAlbum(model, commandArgs);

  }
}