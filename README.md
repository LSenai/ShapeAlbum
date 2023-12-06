# Shape Drawing Assignment (a very basic "paint" app)

## Summary
This was an assignment for my Object-Oriented Development course. The purpose was to practice using the MVC architecture by designing a rudimentary application that can draw shapes using input .txt files as commands. 
My goal for this assignment was to employ some of the design patterns we learned about throughout the course. Broadly speaking, there are three types of design patterns:

* Creational
* Behavioral
* Structural

For this assignment, I made use of the Factory design pattern as a type of Creational pattern to create Shape objects. Most significantly, I centered my design around the Command design pattern, which is a Behavioral pattern. The biggest design feature of my Model is that I utilized the Command pattern to create a method - commandReceiver() - that will handle and delegate nearly all method calls for the Model. I chose this approach because I wanted all interactions with the controller to be handled through one class in my model (the Model class) while still making use of command objects. 

## Reflection - View Implementation 
There are two views,
the designs of which are detailed below:
1. Graphical View: uses Swing to display the shapes and snapshots in a graphical window. As I was new to Swing, this was
the part of the project that took the longest to complete. After reading some of the documentation and watching a few
tutorials, I decided to that my graphical view will make use of three JPanels: a top panel that displays information
about the snapshot, a middle panel the displays the snapshot itself, and then a bottom panel that will host the buttons.

To toggle between the snapshots, I chose to re-render the panels every time a new snapshot is selected. I did this by
using and constantly updating a variable (currentSnapshotIndex) that keeps track of the index of the current snapshot.
I use this index to access the current snapshot from the snapshotList, and then I use the snapshot's getShapes() method
to get the list of shapes that are associated with the snapshot. I then loop through the list of shapes and render them
to the middle panel using the ShapePanel class.

In retrospect, I think this is not the most efficient solution. If I were to start over, I would have pre-rendered the
snapshots and stored them in some sort of array structure. Then, when a new snapshot is selected, I would simply display
 the associated panels from the array. This would have been more efficient because it would have eliminated the need to
re-render the panels every time a new snapshot is selected. And I believe this approach would also be space/time
efficient, and could allow me to save snapshots to a file and then load them back in later, if I wanted to create
that feature.

2. Text View: this view creates a simple static html and svg file that displays the snapshots and associated info. There
are three methods: one that creates the svg code for each shape in the snapshot, another that uses a string builder to
create the html code for the snapshot, and a third that creates the html file and writes the html code into it generated
by the previous two methods. If I wanted to challenge myself more, I could have considered creating "frames" for each
snapshot, or added more dynamic features to the html file.

For the views, I decided to create two separate view interfaces, as they have very different methods, and I could not
think of a way to distill any common features into a single (or parent) interface.

## Reflection -- Model Implementation and SOLID Principles 
I believe that the Command pattern adheres to the SOLID principles in the following ways

* Single Responsibility Principle (SRP): each command object is responsible for encapsulating a specific action and has only one reason to 
* Open-Closed Principle (OCP): the design allows new commands to be added to the system without modifying existing code.
* Interface Segregation Principle (ISP): the Command pattern defines a simple interface that all commands must implement
* Dependency Inversion Principle (DIP): the command objects depend on abstractions, not on concrete classes. This allows for flexibility as new commands can be added to the system without changing existing code.

## Conclusion 
The use of the Command design pattern in this code also helps to maintain the Separation of Concerns principle. By encapsulating the request for an operation as an object, the Model class is able to separate the logic for executing that operation from the client that requested it. This also allows for greater flexibility and extensibility, as new commands can be added without modifying the existing code.

The use of the Command pattern in this code is a trade-off between increased complexity and memory usage, and the benefits of improved maintainability and flexibility. I took this assignment as an opportunity to think deeply about how to implement a design pattern, and hopefully in doing so, will make implementation of the Controller and View more simple for the next assignment.

Ultimately, I am happy with the results of the project, especially the results of the model design and how easy it
made the controller implementation. I think my biggest improvements can be made in the view design and also unit testing
for the controller.
