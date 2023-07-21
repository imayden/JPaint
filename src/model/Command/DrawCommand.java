package model.Command;

// ydeng24@depaul.edu

import model.Point;
import model.ShapeColor;
import model.ShapeShadingType;
import model.DrawnShapesList;
import model.NullObject.NullShape;
import model.Strategy.DrawEllipse;
import model.Strategy.DrawRectangle;
import model.Strategy.DrawShape;
import model.Strategy.DrawTriangle;
import model.interfaces.*;
import view.interfaces.IPaintCanvas;
import java.awt.*;

// User selection for drawing shapes
public class DrawCommand implements ICommand {

    Point startPoint;
    Point endPoint;
    IPaintCanvas paintCanvas;
    IApplicationState appState;
    DrawShape drawShapeContext;
    IShape shapeStrategy;

    public DrawCommand(Point startPoint, Point endPoint, IPaintCanvas paintCanvas,
                       IApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        drawShapeContext =  new DrawShape();
    }
    
    // Get shading type for shape drawing
    private String getShadeType(ShapeShadingType shadingType) {
        switch (shadingType) {
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

    @Override
    public void run() {

        // Select color for shape drawing
        Color primaryColor = ShapeColor.colorsMap.get(appState.getActivePrimaryColor());
        Color secondaryColor = ShapeColor.colorsMap.get(appState.getActiveSecondaryColor());

        // Select shading type for shape drawing
        String shadeType = getShadeType(appState.getActiveShapeShadingType());


        // Strategy Pattern
        IShape shapeStrategy;

        switch (appState.getActiveShapeType()) {
            case RECTANGLE:
                shapeStrategy = new DrawRectangle(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
                break;
            case ELLIPSE:
                shapeStrategy = new DrawEllipse(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
                break;
            case TRIANGLE:
                shapeStrategy = new DrawTriangle(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
                break;
            default:
                // Null Object Pattern 
                shapeStrategy = new NullShape();
                break;
        }


        // Set shape drawing strategy
        drawShapeContext.setDrawShapeStrategy(shapeStrategy);

        // Send shape drawing parameters to the drawShape interface
        drawShapeContext.drawShape();

        // Add drawn shapes to the shape list
        DrawnShapesList.shapeList.add(shapeStrategy);

        // Add shapes to CommandHistory
        CommandHistory.add(shapeStrategy);
    }
}
