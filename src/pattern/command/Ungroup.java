package pattern.command;

// import java.util.List;
// import java.util.ArrayList;
import model.ActiveShape;
import model.ExistingShape;
import pattern.command.interfaces.ICommand;
// import pattern.command.interfaces.IUndoable;
import pattern.composite.ShapeGroup;
import pattern.factory.interfaces.IShape;
import pattern.singleton.MouseListener;
import view.gui.UpdateCanvas;

// ydeng24@depaul.edu

public class Ungroup implements ICommand{

    public void execute() {
        System.out.println("Ungroup");
        for (IShape shape : ActiveShape.activeShape) {
            if (shape instanceof ShapeGroup) {
                ShapeGroup shapeGroup = (ShapeGroup) shape;

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
