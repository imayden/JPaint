package model.command;

import model.command.CommandHandler.DeletionHandler;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class Delete implements IUndoable, ICommand{

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

