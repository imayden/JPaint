package pattern.command;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.interfaces.*;
import pattern.proxy.OutlineHandler;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

// Delete selected shape

public class Delete implements IClipboard, IActiveShape, IUndoable {

    private LinkedList<IShape> shapesToDelete;
    private IPaintCanvas paintCanvas = null;
    private OutlineHandler selectedShapeOutline;

    public Delete() {
        shapesToDelete = new LinkedList<>();
        for (IShape selectedShape : selectedShapes) {
            if (ExistingShape.shapeList.contains(selectedShape)) {
                if (paintCanvas == null)
                    paintCanvas = selectedShape.getPaintCanvas();
                shapesToDelete.add(selectedShape);
            }
        }
    }

    public void run() {
        for (IShape temp : shapesToDelete) {
            selectedShapes.remove(temp);
            ExistingShape.shapeList.remove(temp);
        }

        if (paintCanvas != null) {
            // Update canvas
            UpdateCanvas.update(paintCanvas);
        }
        CommandInvoker.add(this);
    }

    @Override
    public void undo() {
        for (IShape temp : shapesToDelete)
        {
            selectedShapes.add(temp);
            ExistingShape.shapeList.add(temp);
        }
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
            selectedShapeOutline = new OutlineHandler();
            selectedShapeOutline.shapeOutline();
        }
    }

    @Override
    public void redo() {
        this.run();
    }
}
