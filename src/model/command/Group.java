package model.command;

import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.mouseUtilities.MouseListener;

// ydeng24@depaul.edu

public class Group implements ICommand, IUndoable {     
    
    private GroupHandler shapeGroup;

    public void execute() {
        shapeGroup = new GroupHandler(MouseListener.getPaintCanvas());
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
