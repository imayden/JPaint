package model.Command;

// ydeng24@depaul.edu

import model.DrawnShapesList;
import model.Proxy.SelectedShapeOutline;
import model.interfaces.*;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

// Delete selected shape

public class DeleteCommand implements IClipboardCommand, ISelectedShapesList, IUndoable {

    private LinkedList<IShape> shapesToDelete;
    private IPaintCanvas paintCanvas = null;
    private SelectedShapeOutline selectedShapeOutline;

    public DeleteCommand() {
        shapesToDelete = new LinkedList<>();
        for (IShape selectedShape : selectedShapes) {
            if (DrawnShapesList.shapeList.contains(selectedShape)) {
                if (paintCanvas == null)
                    paintCanvas = selectedShape.getPaintCanvas();
                shapesToDelete.add(selectedShape);
            }
        }
    }

    public void run() {
        for (IShape temp : shapesToDelete) {
            selectedShapes.remove(temp);
            DrawnShapesList.shapeList.remove(temp);
        }

        if (paintCanvas != null) {
            // Update canvas
            UpdateCanvas.update(paintCanvas);
        }
        CommandHistory.add(this);
    }

    @Override
    public void undo() {
        for (IShape temp : shapesToDelete)
        {
            selectedShapes.add(temp);
            DrawnShapesList.shapeList.add(temp);
        }
        if (paintCanvas != null) {
            UpdateCanvas.update(paintCanvas);
            selectedShapeOutline = new SelectedShapeOutline();
            selectedShapeOutline.shapeOutline();
        }
    }

    @Override
    public void redo() {
        this.run();
    }
}
