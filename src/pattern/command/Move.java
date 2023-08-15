
package pattern.command;

import model.Point;
import pattern.command.interfaces.IUndoable;
import pattern.factory.interfaces.IShape;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

public class Move implements IUndoable {
    private MoveHandler handler;
    private IPaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private LinkedList<IShape> shapesToMove;
    private LinkedList<IShape> shapesToRemove;

    public Move(Point startPoint, Point endPoint, IPaintCanvas paintCanvas) {
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
