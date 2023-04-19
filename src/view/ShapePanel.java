package view;

import model.Snapshot;
import model.shapes.IShape;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class ShapePanel extends JPanel {
  private int WIDTH;
  private int HEIGHT;
  private Snapshot snapshot;

  public ShapePanel(Snapshot snapshot, int WIDTH, int HEIGHT) {
    this.WIDTH = WIDTH;
    this.HEIGHT = HEIGHT;
    JPanel shapePanel = new JPanel();
    shapePanel.setSize(this.WIDTH, this.HEIGHT);
    shapePanel.setBackground(Color.WHITE);
    this.snapshot = snapshot;
  }

  @Override
  public void paint(Graphics g) {
    Graphics2D g2D = (Graphics2D) g;
    // Snapshot snapshot = (Snapshot) this.snapshots.keySet().toArray()[0];
    LinkedHashMap<String, IShape> shapeList = this.snapshot.getShapes();
    for (IShape shape : shapeList.values()) {
      if (shape.getType().equals("Rectangle")) {
        g2D.setColor( new Color(shape.getColor().getR(), shape.getColor().getG(), shape.getColor().getB()));
        g2D.fillRect((int) shape.getLocation().getX(), (int) shape.getLocation().getY(), shape.getWidth(),
            shape.getHeight());
      } else {
        g2D.setColor( new Color(shape.getColor().getR(), shape.getColor().getG(), shape.getColor().getB()));
        g2D.fillOval((int) shape.getLocation().getX(), (int) shape.getLocation().getY(), shape.getWidth(),
            shape.getHeight());
      }
    }
  }
}
