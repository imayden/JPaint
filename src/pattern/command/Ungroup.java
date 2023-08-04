package pattern.command;

import java.util.List;

import model.ActiveShape;
import model.ExistingShape;
import model.ShapeGroup;
import pattern.command.interfaces.ICommand;
import pattern.factory.interfaces.IShape;
import pattern.singleton.MouseListener;
import view.gui.UpdateCanvas;

// ydeng24@depaul.edu

public class Ungroup implements ICommand {
    // Implementation at Checkin 4
    

    public void execute() {
        System.out.println("Ungroup!");
        for (IShape shape: ActiveShape.activeShape) {
            if (shape instanceof ShapeGroup) {
                ShapeGroup shapeGroup = (ShapeGroup)shape;

                ExistingShape.shapeList.remove(shape);
                for (IShape innerShape: (shapeGroup.getShapeToGroup())) {
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