package command;

// ydeng24@depaul.edu

import model.DrawnShapesList;
import model.Point;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IShape;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;

// Select shape
public class SelectCommand implements ICommand, ISelectedShapesList {
    private IPaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Point minimum;
    private int width;
    private int height;
    private static boolean isSelected = false;

    public SelectCommand(Point startPoint, Point endPoint, IPaintCanvas paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
    }

    @Override
    public void run() {

        MoveCommand.setMoveSelected(false);

        minimum = new Point(Math.min(startPoint.getX(), endPoint.getX()),
                Math.min(startPoint.getY(), endPoint.getY()));

        width = java.lang.Math.abs(startPoint.getX() - endPoint.getX());

        height = java.lang.Math.abs(startPoint.getY() - endPoint.getY());

        selectedShapes.clear();
        isSelected = false;

        UpdateCanvas.update(paintCanvas);
        selectedShapes.clear();

        // Collision Detection Algo.
        for (IShape temp : DrawnShapesList.shapeList)
        {
                if (temp.getStartPointX() < minimum.getX() + width &&
                    temp.getStartPointX() + temp.getWidth() > minimum.getX() &&
                    temp.getStartPointY() < minimum.getY() + height &&
                    temp.getStartPointY() + temp.getHeight() > minimum.getY())
            {
                     // Added the collided shapes on the list
                     selectedShapes.add(temp);

            }
        }

        isSelected = (selectedShapes.size() > 0) ? true : false;

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
