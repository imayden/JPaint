package pattern.factory;

// ydeng24@depaul.edu
// Final Project

import model.Point;
import model.interfaces.IApplicationState;
import pattern.factory.interfaces.IShape;
import pattern.nullObject.NullShape;
import view.interfaces.IPaintCanvas;
import pattern.strategy.*;
import java.awt.Color;

public class ShapeFactory {
    public static IShape createShape(IApplicationState appState, Point startPoint, Point endPoint, IPaintCanvas paintCanvas, String shadeType, Color primaryColor, Color secondaryColor) {
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
