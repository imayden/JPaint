package pattern.command;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.interfaces.*;
import pattern.command.interfaces.IUndoable;
import pattern.factory.interfaces.IShape;
import pattern.proxy.OutlineHandler;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

public class Delete implements IClipboard, IActiveShape, IUndoable {

    private LinkedList<IShape> shapesToDelete;
    private IPaintCanvas paintCanvas = null;
    private OutlineHandler activeShapeOutline;

    public Delete() {
        shapesToDelete = new LinkedList<>();
        ExistingShape.shapeList.stream()
        .filter(activeShape::contains)
        .forEach(selectedShape -> {
            if (paintCanvas == null)
                paintCanvas = selectedShape.getPaintCanvas();
            shapesToDelete.add(selectedShape);
        });
    }
    

    public void execute() {
        shapesToDelete.forEach(shape -> {
            activeShape.remove(shape);
            ExistingShape.shapeList.remove(shape);
        });
    
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
        }
        CommandInvoker.add(this);
    }

    @Override
    public void undo() {
        shapesToDelete.forEach(shape -> {
            activeShape.add(shape);
            ExistingShape.shapeList.add(shape);
        });
    
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
            activeShapeOutline = new OutlineHandler();
            activeShapeOutline.outline();
        }
    }

    @Override
    public void redo() {
        shapesToDelete.forEach(shape -> {
            activeShape.remove(shape);
            ExistingShape.shapeList.remove(shape);
        });
    
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
        }
    }
}
