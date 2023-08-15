package model.command;

import model.mouseUtilities.Point;

// ydeng24@depaul.edu

import view.interfaces.IPaintCanvas;

public class Select {
    private IPaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;

    public Select(Point startPoint, Point endPoint, IPaintCanvas paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
    }

    public void execute() {
        SelectHandler handler = new SelectHandler(startPoint, endPoint, paintCanvas);
        handler.execute();
    }

    // Getters
    public IPaintCanvas getPaintCanvas() {
        return paintCanvas;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }
}


