// package pattern.command;

// // ydeng24@depaul.edu

// import model.MousePoint;
// import model.ShapeColor;
// import model.ShapeShadingType;
// import model.DrawnShapesList;
// import model.interfaces.*;
// import pattern.command.interfaces.ICommand;
// import pattern.nullObject.NullShape;
// import pattern.strategy.Ellipse;
// import pattern.strategy.Rectangle;
// import pattern.strategy.ShapeDrawing;
// import pattern.strategy.Triangle;
// import view.interfaces.IPaintCanvas;
// import java.awt.*;

// // User selection for drawing shapes
// public class Draw implements ICommand {

//     MousePoint startPoint;
//     MousePoint endPoint;
//     IPaintCanvas paintCanvas;
//     IApplicationState appState;
//     ShapeDrawing drawShapeContext;
//     IShape shapeStrategy;

//     public Draw(MousePoint startPoint, MousePoint endPoint, IPaintCanvas paintCanvas,
//                        IApplicationState appState) {
//         this.startPoint = startPoint;
//         this.endPoint = endPoint;
//         this.paintCanvas = paintCanvas;
//         this.appState = appState;
//         drawShapeContext =  new ShapeDrawing();
//     }
    
//     // Get shading type for shape drawing
//     private String getShadeType(ShapeShadingType shadingType) {
//         switch (shadingType) {
//             case FILLED_IN:
//                 return "filled";
//             case OUTLINE:
//                 return "outline";
//             case OUTLINE_AND_FILLED_IN:
//                 return "filledAndOutline";
//             default:
//                 return null;
//         }
//     }

//     @Override
//     public void execute() {

//         // Select color for shape drawing
//         Color primaryColor = ShapeColor.colorsMap.get(appState.getActivePrimaryColor());
//         Color secondaryColor = ShapeColor.colorsMap.get(appState.getActiveSecondaryColor());

//         // Select shading type for shape drawing
//         String shadeType = getShadeType(appState.getActiveShapeShadingType());


//         // Strategy Pattern
//         IShape shapeStrategy;

//         switch (appState.getActiveShapeType()) {
//             case RECTANGLE:
//                 shapeStrategy = new Rectangle(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
//                 break;
//             case ELLIPSE:
//                 shapeStrategy = new Ellipse(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
//                 break;
//             case TRIANGLE:
//                 shapeStrategy = new Triangle(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
//                 break;
//             default:
//                 // Null Object Pattern 
//                 shapeStrategy = new NullShape();
//                 break;
//         }


//         // Set shape drawing strategy
//         drawShapeContext.setDrawShapeStrategy(shapeStrategy);

//         // Send shape drawing parameters to the drawShape interface
//         drawShapeContext.drawShape();

//         // Add drawn shapes to the shape list
//         DrawnShapesList.shapeList.add(shapeStrategy);

//         // Add shapes to CommandHistory
//         CommandInvoker.add(shapeStrategy);
//     }
// }

package pattern.command;

import model.*;
import pattern.nullObject.NullShape;
import pattern.strategy.ShapeDrawing;
import pattern.command.interfaces.*;
import model.interfaces.*;
import view.interfaces.IPaintCanvas;
import pattern.factory.ShapeFactory;
import model.Point;

import java.awt.*;

public class Draw implements ICommand {
    Point startPoint;
    Point endPoint;
    IPaintCanvas paintCanvas;
    IApplicationState appState;
    ShapeDrawing drawShapeContext;

    public Draw(Point startPoint, Point endPoint, IPaintCanvas paintCanvas, IApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.drawShapeContext = new ShapeDrawing();
    }

    @Override
    public void execute() {
        // Get colors and shading type.
        Color primaryColor = ColorUtils.getPrimaryColor(appState);
        Color secondaryColor = ColorUtils.getSecondaryColor(appState);
        String shadeType = ShadeUtils.getShadeType(appState);

        // Create the shape using the Factory Pattern.
        IShape shapeStrategy = ShapeFactory.createShape(appState, startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);

        // Set shape drawing strategy.
        drawShapeContext.setDrawShapeStrategy(shapeStrategy);

        // Draw the shape and add it to the shape list and command history.
        drawShapeContext.drawShape();
        ExistingShape.shapeList.add(shapeStrategy);
        CommandInvoker.add(shapeStrategy);
    }
}

// Utility classes for getting colors and shading types.

class ColorUtils {
    static Color getPrimaryColor(IApplicationState appState) {
        return ShapeColor.colorsMap.get(appState.getActivePrimaryColor());
    }

    static Color getSecondaryColor(IApplicationState appState) {
        return ShapeColor.colorsMap.get(appState.getActiveSecondaryColor());
    }
}

class ShadeUtils {
    static String getShadeType(IApplicationState appState) {
        switch (appState.getActiveShapeShadingType()) {
            case FILLED_IN:
                return "filled";
            case OUTLINE:
                return "outline";
            case OUTLINE_AND_FILLED_IN:
                return "filledAndOutline";
            default:
                return null;
        }
    }
}
