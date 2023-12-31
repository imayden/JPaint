package model.command.CommandHandler;

// Design Pattern Used: Command, Memento

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandInvoker;
import model.interfaces.IActiveShape;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.mouseUtilities.Point;
import model.shapeUtilities.ExistingShape;
import view.gui.APaintCanvas;
import view.gui.UpdateCanvas;

import java.util.LinkedList;

public class MoveHandler implements ICommand, IUndoable, IActiveShape {

    private final APaintCanvas paintCanvas;
    private int xDelta;
    private int yDelta;
    private Point startPoint;
    private Point endPoint;
    private static boolean moveSelected = false;
    private static boolean undoSelected = false;
    private static boolean redoSelected = false;
    private LinkedList<IShape> shapesToMove;
    private LinkedList<IShape> shapesToRemove;

    public MoveHandler(Point startPoint, Point endPoint, APaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.startPoint = startPoint;
        this.endPoint = endPoint;

        shapesToMove = new LinkedList<>();
        shapesToRemove = new LinkedList<>();
    }

    @Override
    public void execute() {
        undoSelected = false;
        redoSelected = false;

        xDelta = endPoint.getX() - startPoint.getX();
        yDelta = endPoint.getY() - startPoint.getY();

        for (IShape shape : activeShape)
        {
            if (ExistingShape.shapeList.contains(shape))
            {
                shapesToRemove.add(shape);
                shape.updateCoordinates(xDelta, yDelta);
                shapesToMove.add(shape);
            }
        }

        for (IShape shape : shapesToRemove)
        {
            activeShape.remove(shape);
            ExistingShape.shapeList.remove(shape);
        }
        
        for (IShape shape : shapesToMove)
        {
            activeShape.add(shape);
            ExistingShape.shapeList.add(shape);
        }

        UpdateCanvas.update(paintCanvas);
        moveSelected = (activeShape.size() > 0) ? true : false;
        CommandInvoker.add(this);
    }


    @Override
    public void undo() {
        redoSelected = false;
        undoSelected = (shapesToMove.size() > 0) ? true : false;
        for (IShape shape : shapesToMove)
        {
            activeShape.remove(shape);
            ExistingShape.shapeList.remove(shape);
        }
        for (IShape shape : shapesToRemove)
        {
            shape.updateCoordinates(0 - xDelta, 0 - yDelta);
            activeShape.add(shape);
            ExistingShape.shapeList.add(shape);
        }
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void redo() {
        undoSelected = false;
        redoSelected = (shapesToMove.size() > 0) ? true : false;

        for (IShape shape : shapesToRemove)
        {
            activeShape.remove(shape);
            ExistingShape.shapeList.remove(shape);
        }
        for (IShape shape : shapesToMove)
        {
            shape.updateCoordinates(xDelta, yDelta);
            activeShape.add(shape);
            ExistingShape.shapeList.add(shape);
        }
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
        }
    }

    public static boolean isMoveSelected() {
        return moveSelected;
    }

    public static void setMoveSelected(boolean moveSelected) {
        MoveHandler.moveSelected = moveSelected;
    }

    public static boolean isUndoSelected() {
        return undoSelected;
    }

    public static boolean isRedoSelected() {
        return redoSelected;
    }

    public static void setUndoSelected(boolean undoSelected) {
        MoveHandler.undoSelected = undoSelected;
    }

    public static void setRedoSelected(boolean redoSelected) {
        MoveHandler.redoSelected = redoSelected;
    }

    public Point getStartPoint() {
        return startPoint;
    }

    public Point getEndPoint() {
        return endPoint;
    }

    public APaintCanvas getPaintCanvas() {
        return paintCanvas;
    }
    
    public LinkedList<IShape> getShapesToMove() {
        return shapesToMove;
    }

    public LinkedList<IShape> getShapesToRemove() {
        return shapesToRemove;
    }
}
