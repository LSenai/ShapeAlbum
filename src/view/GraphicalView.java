package view;

import controller.IAlbumController;
import model.Snapshot;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedHashMap;

public class GraphicalView extends JFrame implements IGraphicalView, ActionListener {

  private JLabel topLabel;
  private JLabel bottomLabel;
  JButton previousButton;
  JButton selectButton;
  JButton nextButton;
  LinkedHashMap<Snapshot, String> snapshots;
  private final int WIDTH;
  private final int HEIGHT;
  final int ICON_SIZE = 25;
  private ShapePanel shapePanel;
  private int currentSnapshotIndex;
  private final String FRAME_TITLE = "Shapes Album";
  private final Color FRAME_BACKGROUND = new Color(14, 46, 46);
  private final Color TOP_PANEL_BACKGROUND = new Color(159, 175, 202);
  private final Color BOTTOM_PANEL_BACKGROUND = new Color(14, 55, 122);

  public GraphicalView(LinkedHashMap<Snapshot, String> snapshots, int WIDTH, int HEIGHT) {

    // set up FRAME
    this.HEIGHT = HEIGHT;
    this.WIDTH = WIDTH;
    this.snapshots = snapshots;
    this.currentSnapshotIndex = 0;

  }
  
  public void display() {
    this.getContentPane().removeAll();
    this.createFrame();
    createTopPanel();
    createBottomPanel();
    Snapshot current = (Snapshot) this.snapshots.keySet().toArray()[currentSnapshotIndex];
    shapePanel = new ShapePanel(current, this.WIDTH, this.HEIGHT); // automatically displays first snapshot
    this.add(shapePanel, BorderLayout.CENTER);

    this.pack();
  }

  /**
   * Helper function to set the current snapshot index as user changes snapshots.
   * @param value the index of the snapshot to be displayed.
   */
  private void setSnapshotIndex(int value) {
    this.currentSnapshotIndex = value;
  }

  /**
   * Helper function to create the Frame. Sets the size, title, and background color.
   */
  private void createFrame() {
    this.setPreferredSize(getPreferredSize());
    this.setResizable(true);
    this.setTitle(FRAME_TITLE);

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setVisible(true);
    ImageIcon icon = new ImageIcon("shapes logo.png");
    this.setIconImage(icon.getImage());

    this.getContentPane().setBackground(FRAME_BACKGROUND); // grey background
  }

  /**
   * Helper function to create the top panel. Sets the title and description of the snapshot.
   */
  private void createTopPanel() {
    // get ID of current snapshot to display it
    Snapshot current = (Snapshot) this.snapshots.keySet().toArray()[currentSnapshotIndex];
    topLabel = new JLabel(current.getID());
    topLabel.setFont(new Font("Serif", Font.BOLD, 18));
    topLabel.setForeground(Color.BLACK);
    // display snapshot description
    JLabel description = new JLabel((current.getDescription()));
    description.setFont(new Font("Serif", Font.ITALIC, 16));
    description.setForeground(Color.BLACK);

    // Create a border with padding
    Border paddingBorder = BorderFactory.createEmptyBorder(5, 15, 5, 10);

    JPanel topPanel = new JPanel(new GridLayout(2, 1)); // 2 rows for ID and description
    topPanel.setBackground(TOP_PANEL_BACKGROUND);
    topPanel.setPreferredSize(new Dimension(WIDTH, 50));

    // Set padding border to each label
    topLabel.setBorder(paddingBorder);
    description.setBorder(paddingBorder);

    topPanel.add(topLabel);
    topPanel.add(description);

    this.add(topPanel, BorderLayout.NORTH);
  }

  /**
   * Helper function to create the bottom panel. Sets the buttons and their actions.
   */
  private void createBottomPanel() {
    bottomLabel = new JLabel();
    bottomLabel.setHorizontalAlignment(SwingConstants.CENTER);

    JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
    bottomPanel.setBackground(BOTTOM_PANEL_BACKGROUND);
    bottomPanel.setPreferredSize(new Dimension(WIDTH, 75));
    bottomPanel.add(previousButton());
    bottomPanel.add(selectButton());
    bottomPanel.add(nextButton());
    bottomPanel.add(bottomLabel);

    this.add(bottomPanel, BorderLayout.SOUTH);
  }

  /**
   * Helper function to create the previous button.
   * @return the previous button as an object.
   */
  private JButton previousButton() {
    previousButton = new JButton("Previous");
    ImageIcon previousIcon = new ImageIcon("back.png");
    previousIcon.setImage(previousIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_AREA_AVERAGING));
    previousButton.setIcon(previousIcon);
    previousButton.addActionListener(this);
    return previousButton;
  }

  /**
   * Helper function to create the select button.
   * @return the select button as an object.
   */
  private JButton selectButton() {
    selectButton = new JButton("Select");
    ImageIcon listIcon = new ImageIcon("list.png");
    listIcon.setImage(listIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_AREA_AVERAGING));
    selectButton.setIcon(listIcon);
    selectButton.addActionListener(this);
    return selectButton;
  }

  /**
   * Helper function to create the next button.
   * @return the next button as an object.
   */
  private JButton nextButton() {
    nextButton = new JButton("Next");
    ImageIcon nextIcon = new ImageIcon("next.png");
    nextIcon.setImage(nextIcon.getImage().getScaledInstance(ICON_SIZE, ICON_SIZE, Image.SCALE_AREA_AVERAGING));
    nextButton.setIcon(nextIcon);
    nextButton.addActionListener(this);
    return nextButton;
  }

  /**
   * Helper function to help set size, as suggested by professor. Cannot set as private.
   * @return
   */
  @Override
  public Dimension getPreferredSize() {
    return new Dimension(WIDTH, HEIGHT);
  }

  /**
   * Manages the actions for all buttons in the graphical view. Checks to see if the user is at the
   * first or last snapshot, and disables the previous or next button accordingly. Calls the display method
   * to display the next or previous snapshot.
   * @param e the event to be processed (click).
   */
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

  /**
   * Helper function to display a dialog box with a message.
   * @param message the message to be displayed.
   */
  private void showDialog(String message) {
    JDialog dialog = new JDialog(this, "Message");
    dialog.setLocationRelativeTo(shapePanel);
    JLabel label = new JLabel(message);
    label.setHorizontalAlignment(SwingConstants.CENTER);
    dialog.add(label);
    dialog.setSize(400, 100);
    dialog.setVisible(true);
  }

  /**
   * Helper function to display a dialog box with a list of snapshots. The user can select a snapshot
   * from the list and the snapshot will be displayed.
   */
  private void showSelectMenu() {
    JDialog dialog = new JDialog(this, "Select a Snapshot");
    dialog.setLayout(new BorderLayout());
    dialog.setSize(200, 150);
    dialog.setLocationRelativeTo(shapePanel);

    JComboBox<String> comboBox = new JComboBox<String>(getSnapshotIDString());
    dialog.add(comboBox, BorderLayout.NORTH);

    JButton ok = new JButton("OK");
    // Lambda expression to set the snapshot index and display the snapshot that is selected.
    ok.addActionListener(e -> {
      int index = comboBox.getSelectedIndex();
      this.setSnapshotIndex(index);
      this.display();
      dialog.dispose();
    });
    dialog.add(ok, BorderLayout.SOUTH);
    dialog.setVisible(true);
  }

  /**
   * Helper function to get the snapshot ID as a string array. Used to display the snapshot options
   * in the select menu.
   * @return the snapshot ID as a string array.
   */
  private String[] getSnapshotIDString() {
    String[] snapshotIDString = new String[this.snapshots.size()];
    int i = 0;
    for (Snapshot snapshot : this.snapshots.keySet()) {
      snapshotIDString[i] = snapshot.getID();
      i++;
    }
    return snapshotIDString;
  }
}
