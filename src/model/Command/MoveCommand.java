package model.Command;

// ydeng24@depaul.edu

import model.DrawnShapesList;
import model.Point;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

// Move shape

public class MoveCommand implements ICommand, IUndoable, ISelectedShapesList {

    private final IPaintCanvas paintCanvas;
    private int xDelta;
    private int yDelta;
    private Point startPoint;
    private Point endPoint;
    private static boolean moveSelected = false;
    private static boolean undoSelected = false;
    private static boolean redoSelected = false;
    private LinkedList<IShape> shapesToMove;
    private LinkedList<IShape> shapesToRemove;

    public MoveCommand(Point startPoint, Point endPoint, IPaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        shapesToMove = new LinkedList<>();
        shapesToRemove = new LinkedList<>();
    }

    @Override
    public void run() {
        undoSelected = false;
        redoSelected = false;

        // Calculating the offset - xDelta and yDelta
        xDelta = endPoint.getX() - startPoint.getX();
        yDelta = endPoint.getY() - startPoint.getY();

        // Updating the Coorodinates
        for (IShape selectedShape : selectedShapes)
        {
            if (DrawnShapesList.shapeList.contains(selectedShape))
            {
                shapesToRemove.add(selectedShape);
                selectedShape.updateCoordinates(xDelta, yDelta);
                shapesToMove.add(selectedShape);
            }
        }

        for (IShape shape : shapesToRemove)
        {
            selectedShapes.remove(shape);
            DrawnShapesList.shapeList.remove(shape);
        }
        
        for (IShape shape : shapesToMove)
        {
            selectedShapes.add(shape);
            DrawnShapesList.shapeList.add(shape);
        }

        // Canvas Refresh
        UpdateCanvas.update(paintCanvas);
        moveSelected = (selectedShapes.size() > 0) ? true : false;
        CommandHistory.add(this);
    }


    @Override
    public void undo() {
        redoSelected = false;
        undoSelected = (shapesToMove.size() > 0) ? true : false;
        for (IShape temp1 : shapesToMove)
        {
            selectedShapes.remove(temp1);
            DrawnShapesList.shapeList.remove(temp1);
        }
        for (IShape temp1 : shapesToRemove)
        {
            temp1.updateCoordinates(0 - xDelta, 0 - yDelta);
            selectedShapes.add(temp1);
            DrawnShapesList.shapeList.add(temp1);
        }
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void redo() {
        undoSelected = false;
        redoSelected = (shapesToMove.size() > 0) ? true : false;

        for (IShape temp1 : shapesToRemove)
        {
            selectedShapes.remove(temp1);
            DrawnShapesList.shapeList.remove(temp1);
        }
        for (IShape temp1 : shapesToMove)
        {
            temp1.updateCoordinates(xDelta, yDelta);
            selectedShapes.add(temp1);
            DrawnShapesList.shapeList.add(temp1);
        }
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
        }
    }

    public static boolean isMoveSelected() {
        return moveSelected;
    }

    public static void setMoveSelected(boolean moveSelected) {
        MoveCommand.moveSelected = moveSelected;
    }

    public static boolean isUndoSelected() {
        return undoSelected;
    }

    public static boolean isRedoSelected() {
        return redoSelected;
    }

    public static void setUndoSelected(boolean undoSelected) {
        MoveCommand.undoSelected = undoSelected;
    }

    public static void setRedoSelected(boolean redoSelected) {
        MoveCommand.redoSelected = redoSelected;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public IPaintCanvas getPaintCanvas() {
        return paintCanvas;
    }
}
