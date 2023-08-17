package model.command;

import model.command.CommandHandler.DrawHandler;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IApplicationState;
import model.interfaces.ICommand;
import model.mouseUtilities.Point;
import view.interfaces.APaintCanvas;


public class Draw implements ICommand {
    private Point startPoint;
    private Point endPoint;
    private APaintCanvas paintCanvas;
    private IApplicationState appState;
    private DrawHandler drawHandler;

    public Draw(Point startPoint, Point endPoint, APaintCanvas paintCanvas, IApplicationState appState) {
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

