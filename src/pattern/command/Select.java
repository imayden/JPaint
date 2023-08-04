package pattern.command;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.Point;
import model.interfaces.IActiveShape;
import pattern.command.interfaces.ICommand;
import pattern.factory.interfaces.IShape;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;

public class Select implements ICommand, IActiveShape {
    private IPaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Point minimum;
    private int width;
    private int height;
    private static boolean isSelected = false;

    public Select(Point startPoint, Point endPoint, IPaintCanvas paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void execute() {

        Move.setMoveSelected(false);

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

        // Collision Detection Algo.
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
        System.out.println("ExistingShape.shapeList.size: " + ExistingShape.shapeList.size());
        System.out.println("activeShape.size: " + activeShape.size());

        isSelected = (activeShape.size() > 0) ? true : false;

    }

    public static boolean isSelected() {
        return isSelected;
    }

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
