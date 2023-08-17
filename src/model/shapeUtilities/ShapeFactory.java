package model.shapeUtilities;

import model.interfaces.IApplicationState;
import model.interfaces.IShape;
import model.mouseUtilities.Point;
import model.nullObject.NullShape;
import model.shapeUtilities.drawShape.*;
import view.interfaces.APaintCanvas;

import java.awt.Color;

public class ShapeFactory {
    public static IShape createShape(IApplicationState appState, Point startPoint, Point endPoint, APaintCanvas paintCanvas, String shadeType, Color primaryColor, Color secondaryColor) {
        switch (appState.getActiveShapeType()) {
            case RECTANGLE:
                return new Rectangle(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
            case ELLIPSE:
                return new Ellipse(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
            case TRIANGLE:
                return new Triangle(startPoint, endPoint, paintCanvas, shadeType, primaryColor, secondaryColor);
            default:
                return new NullShape();
        }
    }
}
