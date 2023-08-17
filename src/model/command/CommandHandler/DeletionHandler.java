package model.command.CommandHandler;

// Design Pattern Used: Command

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandInvoker;
import model.interfaces.*;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShapeOutline.OutlineHandler;
import view.gui.APaintCanvas;
import view.gui.UpdateCanvas;

import java.util.LinkedList;

public class DeletionHandler implements IClipboard, IActiveShape, IUndoable  {

    private LinkedList<IShape> shapesToDelete;
    private APaintCanvas paintCanvas = null;
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
