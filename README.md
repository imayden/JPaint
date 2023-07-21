# JPaint - Paint Application

## About
- Version: 3.0
- Last Update: July 20, 2023
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
#### Strategy Pattern
The application uses the Strategy pattern to define different drawing strategies for shapes (rectangles, ellipses, triangles).
#### Null Object Pattern
The Null Object pattern is implemented to handle cases where no valid shape is selected.
#### Proxy Pattern
The Proxy pattern is used for selected shapes to provide a more efficient way of detecting shape selections.
#### Singleton Pattern
The Singleton pattern is employed for the MouseListener to ensure only one instance is used to handle mouse events.
#### Command Pattern
The application follows the Command pattern to implement undo and redo functionality for shape modifications.

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
css
Copy code
JPaint/src
├── controller/
├── model/
│   ├── Command/
│   ├── Strategy/
│   ├── Null Object/
│   ├── Proxy/
│   ├── Singleton/
│   ├── dialogs/
│   ├── interfaces/
│   └── persistence/
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
