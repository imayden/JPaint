package model.command.CommandHandler;

// Design Pattern Used: Command, State, Observer

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IActiveShape;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.mouseUtilities.Point;
import model.shapeUtilities.ExistingShape;
import view.gui.APaintCanvas;
import view.gui.UpdateCanvas;

public class SelectHandler implements ICommand, IActiveShape {
    private APaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Point minimum;
    private int width;
    private int height;
    private static boolean isSelected = false;

    public SelectHandler(Point startPoint, Point endPoint, APaintCanvas paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void execute() {
        MoveHandler.setRedoSelected(false);

        minimum = new Point(Math.min(startPoint.getX(), endPoint.getX()),
                Math.min(startPoint.getY(), endPoint.getY()));

        width = java.lang.Math.abs(startPoint.getX() - endPoint.getX());

        height = java.lang.Math.abs(startPoint.getY() - endPoint.getY());

        activeShape.clear();
        isSelected = false;

        UpdateCanvas.update(paintCanvas);
        activeShape.clear();

        System.out.println("minimum: " + minimum);
        System.out.println("minimum: " +  minimum);

        for (IShape shape : ExistingShape.shapeList)
        {
                if (shape.getStartPointX() < minimum.getX() + width &&
                    shape.getStartPointX() + shape.getWidth() > minimum.getX() &&
                    shape.getStartPointY() < minimum.getY() + height &&
                    shape.getStartPointY() + shape.getHeight() > minimum.getY())
            {
                     activeShape.add(shape);

            }
        }
        System.out.println("Shapes on the canvas: " + ExistingShape.shapeList.size());
        System.out.println("Active shapes: " + activeShape.size());

        isSelected = (activeShape.size() > 0) ? true : false;

    }

    public static boolean isSelected() {
        return isSelected;
    }

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
