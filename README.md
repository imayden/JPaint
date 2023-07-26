# JPaint - Paint Application

## About
- Version: 3.3
- Last Update: July 26, 2023
- Note: Final Project
- GitHub: https://github.com/imayden/JPaint.git
- Check-In Sprint: Check-In 1, Check-In 2, Check-In 3
- E-Mail: ydeng24@depaul.edu

## Introduction
JPaint is a paint application that allows users to draw various shapes on a canvas. It provides a user-friendly interface and supports multiple functionalities for creating, selecting, and manipulating shapes.

## Features
#### Drawing Shapes (Check-In 1&2)
Users can draw rectangles, ellipses, and triangles on the canvas using the mouse.
#### Undo/Redo (Check-In 1&2&3)
Support for undo and redo operations for shape modifications.
#### Shape Shading (Check-In 2)
Shapes can be filled, outlined, or both, based on user preferences.
#### Color Selection (Check-In 2)
Users can choose primary and secondary colors for drawing shapes.
#### Shape Selection (Check-In 2)
Users can select shapes by clicking on them.
#### Moving Shapes (Check-In 2)
Selected shapes can be moved to new positions on the canvas.
#### Copy/Paste Shapes (Check-In 3)
Users can copy selected shapes and paste them in different locations.

## Design Patterns Used
#### Command Pattern
In this application, the Command Pattern is applied by the classes like Undo and Redo. The application follows the Command pattern to implement undo and redo functionality for shape modifications. The application follows the Command Pattern to implement undo and redo functionality for shape modifications. In this pattern, each operation (for example, undo and redo) is encapsulated as an object. This allows you to store operations or allow undoing operations.
#### Factory Pattern
In this application, the Factory Pattern is applied by the ShapeFactory class. The Factory Pattern is used in this application to create instances of different shapes. The Factory Pattern provides an interface for creating objects but allows subclasses to decide which class to instantiate. In this example, the createShape() method of the ShapeFactory class decides which type of shape to create based on the input parameters.
#### Null Object Pattern
In this application, the Null Object Pattern is applied by the NullShape class. The Null Object Pattern is implemented to handle cases where no valid shape is selected. In this pattern, when a method cannot return a valid object, it returns a null object instead of null. This null object behaves the same as a valid object but usually does not perform any operations. For example, when there is no matching shape type, the createShape() method of ShapeFactory will return a NullShape object.
#### Proxy Pattern
In this application, the Proxy Pattern is applied by the classes like OutlineHandlerProxy and RectangleOutline.The Proxy pattern is used for selected shapes to provide a more efficient way of detecting shape selections. The Proxy Pattern is used for selected shapes to provide a more efficient way of detecting shape selections. The Proxy Pattern provides a proxy for other objects to control access to this object. By using a proxy, we can optimize when performing high-cost operations and only perform these operations when they are truly needed.
#### Singleton Pattern
In this application, the Singleton Pattern is applied by the MouseListener class. The Singleton pattern is employed for the MouseListener to ensure only one instance is used to handle mouse events. The Singleton Pattern is employed for the MouseListener to ensure only one instance is used to handle mouse events. Here, we use the getInstance() method to get the only instance of the MouseListener class. If no instance has been created, getInstance() will call the private constructor MouseListener() to create a new instance. If an instance has already been created, then getInstance() directly returns this instance. This ensures that there is always only one instance of MouseListener.
#### Strategy Pattern
In this application, the Strategy Pattern is applied by the classes like Triangle and Rectangle. For example, the Triangle class uses the Strategy Pattern when it implements the IShape interface. Every class that implements the IShape interface defines a drawShape() method, which can be considered different drawing strategies. In other words, the Strategy Pattern is a strategy on how to draw shapes. We can choose different implementations of IShape based on the actual situation.

## SOLID Principles
The JPaint application adheres to the SOLID principles as follows:
#### Single Responsibility Principle (SRP)
Each class has a clear and single responsibility, promoting easy maintenance and understanding.
#### Open/Closed Principle (OCP)
The application is open for extension through strategies but closed for modification to existing code.
#### Liskov Substitution Principle (LSP)
Subclasses (shape strategies) can be used interchangeably with their parent class (shape drawing).
#### Interface Segregation Principle (ISP)
Interfaces are designed to be specific to the needs of their implementing classes.
#### Dependency Inversion Principle (DIP)
The application relies on abstractions (interfaces) rather than concrete implementations.

## Usage
To use the JPaint application:
1. Ensure you have Oracle Java Open JDK 19 or later installed on your system.
2. Clone or download the JPaint repository from GitHub: JPaint Repository
3. Navigate to the project's root directory in a terminal or command prompt.
4. Compile the Java source files using the command: javac -d bin -cp src src/main/Main.java
5. Run the application using the command: java -cp bin main.Main
6. The JPaint application will launch, and you can start drawing shapes on the canvas by pressing and dragging the mouse.

## File Structure
The project follows the MVC (Model-View-Controller) design pattern, resulting in the following file structure:
```
JPaint/src
├── controller/
├── model/
│   ├── dialogs/
│   ├── interfaces/
│   └── persistence/
├── pattern/
│   ├── command/
│   ├── factory/
│   ├── nullObject/
│   ├── proxy/
│   ├── singleton/
│   ├── strategy/
├── main/
└── view/
    ├── gui/
    └── interfaces/
```
Each directory contains classes and interfaces related to its specific responsibilities.

## Contribution and Feedback
Contributions to JPaint are welcome! Feel free to submit issues or pull requests on the GitHub repository. Your feedback will help improve the application and make it more user-friendly.

## Acknowledgments
I would like to thank all the contributors for their valuable contributions to the JPaint project. Your efforts have helped shape this application into what it is today.

##### Happy painting with JPaint!
