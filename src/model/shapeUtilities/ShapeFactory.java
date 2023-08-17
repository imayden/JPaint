package model.shapeUtilities;

// Design Pattern Used: Factory

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IApplicationState;
import model.interfaces.IShape;
import model.mouseUtilities.Point;
import model.shapeUtilities.drawShape.*;
import model.shapeUtilities.nullObject.NullShape;
import view.gui.APaintCanvas;

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
