package model.command;

// Design Pattern Used: Command

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandHandler.SelectHandler;
import model.interfaces.ICommand;
import model.mouseUtilities.Point;
import view.gui.APaintCanvas;

public class Select implements ICommand {
    private APaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;

    public Select(Point startPoint, Point endPoint, APaintCanvas paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
    }

    public void execute() {
        SelectHandler handler = new SelectHandler(startPoint, endPoint, paintCanvas);
        handler.execute();
    }

    // Getters
    public APaintCanvas getPaintCanvas() {
        return paintCanvas;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}


