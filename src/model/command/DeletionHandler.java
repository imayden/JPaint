package model.command;

import model.interfaces.*;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShapeOutline.OutlineHandler;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

// Handler class that manages the specific delete actions
public class DeletionHandler implements IClipboard, IActiveShape, IUndoable  {

    private LinkedList<IShape> shapesToDelete;
    private IPaintCanvas paintCanvas = null;
    private OutlineHandler activeShapeOutline;

    public DeletionHandler() {
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
