// package command;

// // ydeng24@depaul.edu

// import model.Point;
// import model.MouseMode;
// import model.Proxy.SelectedOutlineProxy;
// import model.interfaces.IApplicationState;
// import model.interfaces.ICommand;
// import model.interfaces.ISelectedShapeOutline;
// import view.interfaces.IPaintCanvas;

// // Command Pattern
// public class MouseModeExecution {

//     public static void clickedMode(Point startPoint, Point endPoint,
//                                    IPaintCanvas paintCanvas, IApplicationState appState) {
//         ICommand command = null;
//         SelectedOutlineProxy selectShapeProxy = null;
//         SelectedOutlineProxy moveShapeProxy = null;

//         if (appState.getActiveStartAndEndPointMode() == MouseMode.DRAW) {
//             command = new DrawCommand(startPoint, endPoint, paintCanvas, appState);
//             command.run();
//         }
        
//         // Proxy Pattern
//         else if (appState.getActiveStartAndEndPointMode() == MouseMode.SELECT) {
//             command = new SelectCommand(startPoint, endPoint, paintCanvas);
//             command.run();
//             selectShapeProxy = new SelectedOutlineProxy(command);
//             printShapeOutline(selectShapeProxy);
//         }
//         else if (appState.getActiveStartAndEndPointMode() == MouseMode.MOVE) {
//             command = new MoveCommand(startPoint, endPoint, paintCanvas);
//             command.run();
//             moveShapeProxy = new SelectedOutlineProxy(command);
//             printShapeOutline(moveShapeProxy);
//         }
//     }

//     // Proxy pattern
//     public static void printShapeOutline(ISelectedShapeOutline outlineOperation) {
//         outlineOperation.shapeOutline();
//     }
// }

package command;

import model.Point;
import model.MouseMode;
import model.Proxy.SelectedOutlineProxy;
import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapeOutline;
import view.interfaces.IPaintCanvas;

public class MouseModeExecution {

    public static void clickedMode(Point startPoint, Point endPoint,
                                   IPaintCanvas paintCanvas, IApplicationState appState) {

        ICommand command = getCommand(appState.getActiveStartAndEndPointMode(), startPoint, endPoint, paintCanvas, appState);
        if (command == null) {
            return;
        }

        command.run();

        // Use Proxy pattern only when we are either in Select mode or Move mode.
        if (appState.getActiveStartAndEndPointMode() == MouseMode.SELECT
                || appState.getActiveStartAndEndPointMode() == MouseMode.MOVE) {
            SelectedOutlineProxy shapeProxy = new SelectedOutlineProxy(command);
            printShapeOutline(shapeProxy);
        }
    }

    // A separate function to return command based on the mode.
    private static ICommand getCommand(MouseMode mode, Point startPoint, Point endPoint, IPaintCanvas paintCanvas, IApplicationState appState) {
        ICommand command = null;
        switch (mode) {
            case DRAW:
                command = new DrawCommand(startPoint, endPoint, paintCanvas, appState);
                break;
            case SELECT:
                command = new SelectCommand(startPoint, endPoint, paintCanvas);
                break;
            case MOVE:
                command = new MoveCommand(startPoint, endPoint, paintCanvas);
                break;
            default:
                break;
        }
        return command;
    }

    public static void printShapeOutline(ISelectedShapeOutline outlineOperation) {
        outlineOperation.shapeOutline();
    }
}
