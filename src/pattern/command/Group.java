package pattern.command;

import pattern.command.interfaces.ICommand;
import pattern.command.interfaces.IUndoable;
import pattern.composite.GroupHandler;
import pattern.singleton.MouseListener;

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
