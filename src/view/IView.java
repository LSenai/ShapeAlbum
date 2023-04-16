package view;

import controller.IAlbumController;
import model.Snapshot;

import java.util.LinkedHashMap;

public interface IView {

  void getController(IAlbumController controller);
  LinkedHashMap<Snapshot, String> getSnapshot();

}
