package pattern.command;

import model.*;
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

        Color primaryColor = ColorUtils.getPrimaryColor(appState);
        Color secondaryColor = ColorUtils.getSecondaryColor(appState);
        String shadingType = ShadeUtils.getShadingType(appState);

        IShape shapeStrategy = ShapeFactory.createShape(appState, startPoint, endPoint, paintCanvas, shadingType, primaryColor, secondaryColor);

        drawShapeContext.setDrawShapeStrategy(shapeStrategy);

        drawShapeContext.drawShape();
        ExistingShape.shapeList.add(shapeStrategy);
        CommandInvoker.add(shapeStrategy);
    }
}

class ColorUtils {
    static Color getPrimaryColor(IApplicationState appState) {
        return ShapeColor.colorsMap.get(appState.getActivePrimaryColor());
    }

    static Color getSecondaryColor(IApplicationState appState) {
        return ShapeColor.colorsMap.get(appState.getActiveSecondaryColor());
    }
}

class ShadeUtils {
    static String getShadingType(IApplicationState appState) {
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
