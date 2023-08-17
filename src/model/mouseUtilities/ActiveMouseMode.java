package model.mouseUtilities;

import model.command.Draw;
import model.command.CommandHandler.MoveHandler;
import model.command.CommandHandler.SelectHandler;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.IOutlineHandler;
import model.shapeUtilities.drawShapeOutline.OutlineHanderProxy;
import view.interfaces.APaintCanvas;

public class ActiveMouseMode {

    public static void clickedMode(Point startPoint, Point endPoint, APaintCanvas paintCanvas, IApplicationState appState) {

        ICommand command = getCommand(appState.getActiveMouseMode(), startPoint, endPoint, paintCanvas, appState);
        if (command == null) {
            return;
        }

        command.execute();

        if (appState.getActiveMouseMode() == MouseMode.SELECT
                || appState.getActiveMouseMode() == MouseMode.MOVE) {
            OutlineHanderProxy shapeProxy = new OutlineHanderProxy(command);
            printShapeOutline(shapeProxy);
        }
    }

    private static ICommand getCommand(MouseMode mode, Point startPoint, Point endPoint, APaintCanvas paintCanvas, IApplicationState appState) {
        ICommand command = null;
        switch (mode) {
            case DRAW:
                command = new Draw(startPoint, endPoint, paintCanvas, appState);
                break;
            case SELECT:
                // command = new Select(startPoint, endPoint, paintCanvas);
                command = new SelectHandler(startPoint, endPoint, paintCanvas);
                break;
            case MOVE:
                // command = new Move(startPoint, endPoint, paintCanvas);
                command = new MoveHandler(startPoint, endPoint, paintCanvas);
                break;
            default:
                break;
        }
        return command;
    }

    public static void printShapeOutline(IOutlineHandler outlineOperation) {
        outlineOperation.outline();
    }
}
