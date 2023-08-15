package model.command;

import model.*;
import model.interfaces.*;
import model.mouseUtilities.Point;
import model.shadeUtilities.ShadeUtils;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.ShapeFactory;
import model.shapeUtilities.drawShape.ShapeDrawing;
import view.interfaces.IPaintCanvas;
import model.colorUtilities.ColorUtils;

import java.awt.*;

public class DrawHandler {
    private Point startPoint;
    private Point endPoint;
    private IPaintCanvas paintCanvas;
    private IApplicationState appState;
    private ShapeDrawing drawShapeContext;

    // Constructor Injection Pattern
    public DrawHandler(Point startPoint, Point endPoint, IPaintCanvas paintCanvas, IApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.drawShapeContext = new ShapeDrawing();
    }

    public void handleDraw() {
        Color primaryColor = ColorUtils.getPrimaryColor(appState);
        Color secondaryColor = ColorUtils.getSecondaryColor(appState);
        String shadingType = ShadeUtils.getShadingType(appState);

        // Factory Pattern
        IShape shapeStrategy = ShapeFactory.createShape(appState, startPoint, endPoint, paintCanvas, shadingType, primaryColor, secondaryColor);

        // Strategy Pattern
        drawShapeContext.setDrawShapeStrategy(shapeStrategy);
        drawShapeContext.drawShape();

        ExistingShape.shapeList.add(shapeStrategy);
        CommandInvoker.add(shapeStrategy);
    }
}

