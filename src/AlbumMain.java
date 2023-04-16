import model.Model;
import model.Snapshot;
import model.shapes.IShape;

import java.util.LinkedHashMap;

public class AlbumMain {
  public static void main(String[] args) {
/*
    Point2D location = new Point2D.Double(3, 4);
    Color color = new Color(0, 0, 0);
    IShape rectangle = new Rectangle("myRect", 10, 20, location, color);


    Point2D location2 = new Point2D.Double(6, 5);
    IShape oval = new Oval("myOval", 10, 10, location2, color);

    System.out.println(rectangle.toString());
    System.out.println(oval.toString());
*/

    Model model = new Model();

    model.commandReceiver("shape myRect rectangle 3 4 10 20 0 0.1 0");
    model.commandReceiver("shape myOval oval 6 5 10 10 0 0 0");


    LinkedHashMap<String, IShape> shapes = model.getShapes();
    LinkedHashMap<Snapshot, String> snapList = model.getSnapshots();

    System.out.println(shapes.values().toString());


    /*
    System.out.println(shapes.values().toString());
    System.out.println(snapList.keySet().toString());
    Command takeSnapshot = new takeSnapshot(model.getShapes(), model.getSnapshots());
    takeSnapshot.execute("Snapshot here we go!");
    System.out.println(snapList.keySet().toString());

    Command resize = new ResizeMod(model.getShapes().get("myRect"));
    resize.execute("resize myRect 69 69");
    takeSnapshot.execute("Snapshot snap2");
    System.out.println(snapList.keySet().toString());
*/

/*


    model.commandReceiver("snapshot here we go!");

    System.out.println("Printing list of snapshots\n");

    System.out.println(snapList.keySet().toString());

    // move oval and rectangle
    System.out.println("Moving oval and rectangle\n");
    model.commandReceiver("move myOval 69 69");
    model.commandReceiver("move myRect 69 69");
    model.commandReceiver("snapshot 2nd snap");
    snapList = model.getSnapshots();
    System.out.println(snapList.keySet().toString());
    model.commandReceiver("color myOval 255 255 255");
    model.commandReceiver("color myRect 255 255 255");
    model.commandReceiver("snapshot 3rd snap");
    snapList = model.getSnapshots();
    System.out.println(snapList.keySet().toString());
    System.out.println("These are the shapes! SHAPE EM UP \n" + model.getShapes().values());
*/

  }
}