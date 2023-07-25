package pattern.command;

// ydeng24@depaul.edu

import model.Point;
import model.MouseMode;
import model.interfaces.IApplicationState;
import pattern.command.interfaces.ICommand;
import pattern.proxy.OutlineHanderProxy;
import pattern.proxy.interfaces.IOutlineHandler;
import view.interfaces.IPaintCanvas;

public class SelectMouseMode {

    public static void clickedMode(Point startPoint, Point endPoint,
                                   IPaintCanvas paintCanvas, IApplicationState appState) {

        ICommand command = getCommand(appState.getActiveStartAndEndPointMode(), startPoint, endPoint, paintCanvas, appState);
        if (command == null) {
            return;
        }

        command.execute();

        if (appState.getActiveStartAndEndPointMode() == MouseMode.SELECT
                || appState.getActiveStartAndEndPointMode() == MouseMode.MOVE) {
            OutlineHanderProxy shapeProxy = new OutlineHanderProxy(command);
            printShapeOutline(shapeProxy);
        }
    }

    private static ICommand getCommand(MouseMode mode, Point startPoint, Point endPoint, IPaintCanvas paintCanvas, IApplicationState appState) {
        ICommand command = null;
        switch (mode) {
            case DRAW:
                command = new Draw(startPoint, endPoint, paintCanvas, appState);
                break;
            case SELECT:
                command = new Select(startPoint, endPoint, paintCanvas);
                break;
            case MOVE:
                command = new Move(startPoint, endPoint, paintCanvas);
                break;
            default:
                break;
        }
        return command;
    }

    public static void printShapeOutline(IOutlineHandler outlineOperation) {
        outlineOperation.shapeOutline();
    }
}
