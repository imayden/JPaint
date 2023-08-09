package pattern.command;

// ydeng24@depaul.edu

import pattern.command.interfaces.IUndoable;

// Command class that uses DeletionHandler to execute the delete operation
public class Delete implements IUndoable {

    private DeletionHandler handler;
    
    public Delete() {
        handler = new DeletionHandler();
    }

    public void execute() {
        handler.execute();
    }

    @Override
    public void undo() {
        handler.undo();
    }

    @Override
    public void redo() {
        handler.redo();
    }
}

