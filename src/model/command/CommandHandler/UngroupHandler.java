package model.command.CommandHandler;

// Design Pattern Used: Composite, Command, State, Memento, Observer 

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IShape;
import model.mouseUtilities.MouseListener;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import view.gui.UpdateCanvas;
import java.util.ArrayList;
import java.util.List;

public class UngroupHandler {

    private final List<IShape> ungroupedShapes = new ArrayList<>();

    public void execute() {
        System.out.println("Ungroup");

        for (IShape shape : ActiveShape.activeShape) {
            if (shape instanceof GroupHandler) {
                GroupHandler shapeGroup = (GroupHandler) shape;
                ExistingShape.shapeList.remove(shape);
                for (IShape innerShape : shapeGroup.getShapeToGroup()) {
                    if (!ExistingShape.shapeList.contains(innerShape)) {
                        ExistingShape.shapeList.add(innerShape);
                    }
                }
                ungroupedShapes.add(shapeGroup);
            }
        }

        ActiveShape.activeShape.clear();
        UpdateCanvas.update(MouseListener.getPaintCanvas());
    }

    public void undo() {
        for (IShape shape : ungroupedShapes) {
            if (!ExistingShape.shapeList.contains(shape)) {
                ExistingShape.shapeList.add(shape);
            }
            if (shape instanceof GroupHandler) {
                GroupHandler shapeGroup = (GroupHandler) shape;
                for (IShape innerShape : shapeGroup.getShapeToGroup()) {
                    ExistingShape.shapeList.remove(innerShape);
                }
            }
        }
        UpdateCanvas.update(MouseListener.getPaintCanvas());
    }

    public void redo() {
        execute();
    }
}
