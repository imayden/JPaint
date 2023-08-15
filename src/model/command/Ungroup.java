package model.command;

import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.mouseUtilities.MouseListener;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import view.gui.UpdateCanvas;

// ydeng24@depaul.edu

public class Ungroup implements ICommand{

    public void execute() {
        System.out.println("Ungroup");
        for (IShape shape : ActiveShape.activeShape) {
            if (shape instanceof GroupHandler) {
                GroupHandler shapeGroup = (GroupHandler) shape;

                ExistingShape.shapeList.remove(shape);
                for (IShape innerShape : (shapeGroup.getShapeToGroup())) {
                    if (!ExistingShape.shapeList.contains(innerShape)) {
                        ExistingShape.shapeList.add(innerShape);
                    }

                }
            }
        }
        ActiveShape.activeShape.clear();
        UpdateCanvas.update(MouseListener.getPaintCanvas());

    }
    
}
