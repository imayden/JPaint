
package model.command;

import model.command.CommandHandler.MoveHandler;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.mouseUtilities.Point;
import view.gui.UpdateCanvas;
import view.interfaces.APaintCanvas;
import java.util.LinkedList;

public class Move implements IUndoable {
    private MoveHandler handler;
    private APaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private LinkedList<IShape> shapesToMove;
    private LinkedList<IShape> shapesToRemove;

    public Move(Point startPoint, Point endPoint, APaintCanvas paintCanvas) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        handler = new MoveHandler(startPoint, endPoint, paintCanvas);
        this.shapesToMove = handler.getShapesToMove();
        this.shapesToRemove = handler.getShapesToRemove();
    }

    @Override
    public void undo() {
        handler.undo();
        UpdateCanvas.update(paintCanvas);
    }

    @Override
    public void redo() {
        handler.redo();
        UpdateCanvas.update(paintCanvas);
    }
}
