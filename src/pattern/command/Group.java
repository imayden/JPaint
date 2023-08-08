package pattern.command;

import pattern.command.interfaces.ICommand;
import pattern.command.interfaces.IUndoable;
import pattern.composite.ShapeGroup;
import pattern.singleton.MouseListener;

// ydeng24@depaul.edu

public class Group implements ICommand, IUndoable {     
    
    private ShapeGroup shapeGroup;

    public void execute() {
        shapeGroup = new ShapeGroup(MouseListener.getPaintCanvas());
        shapeGroup.group();
        CommandInvoker.add(this); 
    }

    @Override
    public void undo() {
        shapeGroup.undo();
    }

    @Override
    public void redo() {
        shapeGroup.redo();
    }

    
}
