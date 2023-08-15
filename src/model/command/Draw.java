package model.command;

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.mouseUtilities.Point;
import view.interfaces.IPaintCanvas;


public class Draw implements ICommand {
    private Point startPoint;
    private Point endPoint;
    private IPaintCanvas paintCanvas;
    private IApplicationState appState;
    private DrawHandler drawHandler;

    // Constructor Injection Pattern
    public Draw(Point startPoint, Point endPoint, IPaintCanvas paintCanvas, IApplicationState appState) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.appState = appState;
        this.drawHandler = new DrawHandler(startPoint, endPoint, paintCanvas, appState);
    }

    @Override
    public void execute() {
        drawHandler.handleDraw();
    }
}

