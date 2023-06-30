# Photo-Album Assignment 8 - The Model
## Summary

The assignment is an implementation of the Model for a simple "photo" album application. It is more akin to a simple paint application in that we can create and manipulate shapes, and then take "snapshots" of them to store their current state.

My goal for this assignment was to employ some of the design patterns we learned about throughout the course. Broadly speaking, there are three types of design patterns1:

* Creational
* Behavioral
* Structural

For this assignment, I made use of the Factory design pattern as a type of Creational pattern to create Shape objects. Most significantly, I centered my design around the Command design pattern, which is a Behavioral pattern. The biggest design feature of my Model is that I utilized the Command pattern to create a method - commandReceiver() - that will handle and delegate nearly all method calls for the Model.
## Reasoning

I chose this approach because I wanted all interactions with the controller to be handled through one class in my model (the Model class) while still making use of command objects. Moreover, I wanted to run all commands from the controller to 6 methods:

* commandReceiver(String command) -- this method will act as the "receiver" for all commands coming from the controller that result in state changes. With it, the client can create, move, remove, resize, recolor, and take snapshots.
* clear() -- this method clears all shapes and snapshots from the application.
* getShapes() and getSnapshots() -- returns the present shapes list and snapshot list. These will represent the "state" of the application. Any changes to shapes or snapshots will be a state change.
* printShapes() and printSnapshots() -- both just print the current state as is.

As you can see, the commandReceiver() method effectively handles all state changes. My hope is that this would make controller implementation a lot easier as I will only have to worry about passing along strings as commands to the model to invoke any state changes. I envision a simple/elegant controller implementation that may look something like:

    Model m = new Model()

    while ApplicationIsRunning:
        //...communicate with view
        // read in user commands as strings
    
    m.CommandReceiver(command) // all state-changing commands use this one function
    m.getShapes() // communicate out current state

This still adheres to the Command design pattern as the Controller will still be the "invoker". I use a "receiver" method in the Model that will delegate commands to the relevant command objects.

I thought this approach better models the desired architecture below:

... rather than having multiple Command classes communicating with the Controller, which may look more like:

## Reflection

As we learned in class, the Command pattern encapsulates a request as an object and isolates the command logic into a set of classes. This will make it easier to track, log, or "undo" commands, which may be useful for the implementation of the controller or view components of this application. In attempting to utilize this pattern, I wanted to learn more about how to implement it, and how it may relate to the SOLID principles. I believe that the Command pattern adheres to the SOLID principles in the following ways:

* Single Responsibility Principle (SRP): each command object is responsible for encapsulating a specific action and has only one reason to change.
* Open-Closed Principle (OCP): the design allows new commands to be added to the system without modifying existing code.
* Interface Segregation Principle (ISP): the Command pattern defines a simple interface that all commands must implement.
* Dependency Inversion Principle (DIP): the command objects depend on abstractions, not on concrete classes. This allows for flexibility as new commands can be added to the system without changing existing code.

However, in my implementation, I did choose to lightly violate the dependency inversion principle somewhat. In that in my model class, I have a central "commandReceiver" that I use to delegate nearly all state-changing tasks out to command objects (except clearing everything). Adding new commands therefore requires some minimal editing to the model class to add into the if statements. However, in theory, new commands could be added without using the commandReceiver method or changing the existing code. Ultimately, I thought this decision made for a more clean interaction between the Model and the Controller which will be implemented later.

As a final reflection, I may consider changing (or modifying) the data structure used for the Snapshot lists, depending on requirements for the View/Controller implementation.
## Conclusion

Overall, in terms of SOLID principles, the Model class is designed with several considerations in mind. The Single Responsibility Principle (SRP) is satisfied, as the class has only one reason to change, which is when the state of the model is updated. The Open/Closed Principle (OCP) is also satisfied, as the class is open for extension (by allowing new shapes to be added to the list) but closed for modification, meaning that the implementation of the class will not be changed if new shapes are added.

The use of the Command design pattern in this code also helps to maintain the Separation of Concerns principle. By encapsulating the request for an operation as an object, the Model class is able to separate the logic for executing that operation from the client that requested it. This also allows for greater flexibility and extensibility, as new commands can be added without modifying the existing code.

However, there are trade-offs to consider when using the Command design pattern. One is that it can introduce additional complexity to the codebase, especially if there are many different commands that need to be executed. In this code, for example, there are several commands that modify the shape list, and each command requires a separate Command object to be instantiated and executed.

Overall, the use of the Command pattern in this code is a trade-off between increased complexity and memory usage, and the benefits of improved maintainability and flexibility. I took this assignment as an opportunity to think deeply about how to implement a design pattern, and hopefully in doing so, will make implementation of the Controller and View more simple for the next assignment.
Footnotes

    https://www.freecodecamp.org/news/the-basic-design-patterns-all-developers-need-to-know/ ↩

