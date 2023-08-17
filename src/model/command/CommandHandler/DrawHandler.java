package model.command.CommandHandler;

// Design Pattern Used: Constructor Injection, Factory, Strategy, Command 

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.*;
import model.mouseUtilities.Point;
import model.shadeUtilities.ShadeUtils;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.ShapeFactory;
import model.shapeUtilities.drawShape.ShapeDrawing;
import view.interfaces.APaintCanvas;
import model.colorUtilities.ColorUtils;
import model.command.CommandInvoker;

import java.awt.*;

public class DrawHandler {
    private Point startPoint;
    private Point endPoint;
    private APaintCanvas paintCanvas;
    private IApplicationState appState;
    private ShapeDrawing drawShapeContext;

    // Constructor Injection Pattern
    public DrawHandler(Point startPoint, Point endPoint, APaintCanvas paintCanvas, IApplicationState appState) {
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

