package model.Command;

// ydeng24@depaul.edu

import model.Point;
import model.StartAndEndPointMode;
import model.Proxy.SelectedOutlineProxy;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapeOutline;
import view.interfaces.IPaintCanvas;

// Command Pattern
public class SelectModeOption {

    public static void clickedMode(Point startPoint, Point endPoint,
                                   IPaintCanvas paintCanvas, IApplicationState appState) {
        ICommand command = null;
        SelectedOutlineProxy selectShapeProxy = null;
        SelectedOutlineProxy moveShapeProxy = null;

        if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.DRAW) {
            command = new DrawCommand(startPoint, endPoint, paintCanvas, appState);
            command.run();
        }
        
        // Proxy Pattern
        else if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.SELECT) {
            command = new SelectCommand(startPoint, endPoint, paintCanvas);
            command.run();
            selectShapeProxy = new SelectedOutlineProxy(command);
            printShapeOutline(selectShapeProxy);
        }
        else if (appState.getActiveStartAndEndPointMode() == StartAndEndPointMode.MOVE) {
            command = new MoveCommand(startPoint, endPoint, paintCanvas);
            command.run();
            moveShapeProxy = new SelectedOutlineProxy(command);
            printShapeOutline(moveShapeProxy);
        }
    }

    // Proxy pattern
    public static void printShapeOutline(ISelectedShapeOutline outlineOperation) {
        outlineOperation.shapeOutline();
    }
}
