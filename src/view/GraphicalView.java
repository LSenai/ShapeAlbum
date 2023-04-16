package view;

import controller.IAlbumController;
import model.Snapshot;

import java.util.LinkedHashMap;

public class GraphicalView implements IView {

  IAlbumController controller;
  LinkedHashMap<Snapshot, String> snapshots;

  @Override
  public void getController(IAlbumController controller) {
    this.controller = controller;
  }

  @Override
  public LinkedHashMap<Snapshot, String> getSnapshot() {
    return null;
  }
}
