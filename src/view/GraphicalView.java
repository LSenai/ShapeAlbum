package view;

import controller.IAlbumController;
import model.Snapshot;
import model.shapes.IShape;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class GraphicalView extends JFrame implements IView, ActionListener {

  IAlbumController controller;
  private JLabel topLabel;
  private JLabel bottomLabel;
 // private JPanel shapePanel;
  JButton previousButton;
  JButton selectButton;
  JButton nextButton;
  JButton okButton;
  LinkedHashMap<Snapshot, String> snapshots;
  final int WIDTH = 800;
  final int HEIGHT = 800;
  final int ICON_SIZE = 25;
  private ShapePanel shapePanel;

  private int currentSnapshotIndex;

  public GraphicalView(LinkedHashMap<Snapshot, String> snapshots) {

    // set up FRAME
    this.createFrame();
    this.snapshots = snapshots;
    this.currentSnapshotIndex = 0;
    this.display();

  }
  
  public void display() {
    this.getContentPane().removeAll();
    createTopPanel();
    createBottomPanel();
//    this.snapshots = snapshots;
    shapePanel = new ShapePanel((Snapshot) this.snapshots.keySet().toArray()[currentSnapshotIndex]); // automatically displays first snapshot
    this.add(shapePanel, BorderLayout.CENTER);

    this.pack();

  }

  private void setSnapshotIndex(int value) {
    this.currentSnapshotIndex = value;
  }

  public void createFrame() {
    this.setPreferredSize(getPreferredSize());
    this.setResizable(true);
    this.setTitle("Shape Album");

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    ImageIcon icon = new ImageIcon("shapes logo.png");
    this.setIconImage(icon.getImage());

    this.getContentPane().setBackground(new Color(14, 46, 46)); // grey background
  }

  public JPanel createShapePanel() {
    JPanel shapePanel = new JPanel();
    shapePanel.setSize(WIDTH, HEIGHT);
    return shapePanel;
  }

  private void createTopPanel() {
    Snapshot current = (Snapshot) this.snapshots.keySet().toArray()[currentSnapshotIndex];
    topLabel = new JLabel(current.getID());
    topLabel.setFont(new Font("Serif", Font.BOLD, 18));
    topLabel.setForeground(Color.BLACK);

    JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    topPanel.setBackground(new Color(159, 175, 202));
    topPanel.setPreferredSize(new Dimension(WIDTH, 50));
    topPanel.add(topLabel);

    this.add(topPanel, BorderLayout.NORTH);
  }

  private void createBottomPanel() {
    bottomLabel = new JLabel();
    bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.setBackground(new Color(14, 55, 122));
    bottomPanel.setPreferredSize(new Dimension(WIDTH, 50));
    bottomPanel.add(previousButton());
    bottomPanel.add(selectButton());
    bottomPanel.add(nextButton());
    bottomPanel.add(bottomLabel);

    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  private JButton previousButton() {
    previousButton = new JButton("Previous");
    ImageIcon previousIcon = new ImageIcon("back.png");
    previousIcon.setImage(previousIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_AREA_AVERAGING));
    previousButton.setIcon(previousIcon);
    previousButton.addActionListener(this);
    return previousButton;
  }

  private JButton selectButton() {
    selectButton = new JButton("Select");
    ImageIcon listIcon = new ImageIcon("list.png");
    listIcon.setImage(listIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_AREA_AVERAGING));
    selectButton.setIcon(listIcon);
    selectButton.addActionListener(this);
    return selectButton;
  }

  private JButton nextButton() {
    nextButton = new JButton("Next");
    ImageIcon nextIcon = new ImageIcon("next.png");
    nextIcon.setImage(nextIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_AREA_AVERAGING));
    nextButton.setIcon(nextIcon);
    nextButton.addActionListener(this);
    return nextButton;
  }

  private JButton okButton() {
    okButton = new JButton("OK");
    okButton.addActionListener(this);
    return okButton;
  }

  @Override
  public void getController(IAlbumController controller) {
    this.controller = controller;
  }

  @Override
  public LinkedHashMap<Snapshot, String> getSnapshot() {
    return this.snapshots;
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  @Override
  public void actionPerformed(ActionEvent e) {

    if (this.currentSnapshotIndex >= 0 && this.currentSnapshotIndex <= this.snapshots.size() - 1) {
      previousButton.setEnabled(true);
      nextButton.setEnabled(true);
    }

    String frontMessage = "This is the first snapshot. No more to display prior to this one.";
    String backMessage = "This is the last snapshot. No more to display after this one.";

    if (e.getSource() == previousButton) {
      if (this.currentSnapshotIndex == 0) {
        previousButton.setEnabled(false);
        showDialog(frontMessage);
        return;
      } else {
        this.setSnapshotIndex(this.currentSnapshotIndex - 1);
        this.display();
      }
    } else if (e.getSource() == selectButton) {
      showSelectMenu();
    } else if (e.getSource() == nextButton) {
      if (this.currentSnapshotIndex == this.snapshots.size() - 1) {
        nextButton.setEnabled(false);
        showDialog(backMessage);
        return;
      } else {
        this.setSnapshotIndex(this.currentSnapshotIndex + 1);
        this.display();
      }
    }
  }

  public void showDialog(String message) {
    JDialog dialog = new JDialog(this, "Message");
    dialog.setLocationRelativeTo(shapePanel);
    JLabel label = new JLabel(message);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    dialog.add(label);
    dialog.setSize(400, 100);
    dialog.setVisible(true);
  }

  public void showSelectMenu() {
    JDialog dialog = new JDialog(this, "Select a Snapshot");
    dialog.setLayout(new BorderLayout());
    dialog.setSize(200, 150);
    dialog.setLocationRelativeTo(shapePanel);

    JComboBox<String> comboBox = new JComboBox<String>(getSnapshotIDString());
    dialog.add(comboBox, BorderLayout.NORTH);

    JButton ok = new JButton("OK");
    ok.addActionListener(e -> {
      int index = comboBox.getSelectedIndex();
      this.setSnapshotIndex(index);
      this.display();
      dialog.dispose();
    });
    dialog.add(ok, BorderLayout.SOUTH);
    dialog.setVisible(true);
  }

  public String[] getSnapshotIDString() {
    String[] snapshotIDString = new String[this.snapshots.size()];
    int i = 0;
    for (Snapshot snapshot : this.snapshots.keySet()) {
      snapshotIDString[i] = snapshot.getID();
      i++;
    }
    return snapshotIDString;
  }
}
