package model.command;

// Design Pattern Used: Command

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandHandler.UngroupHandler;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;

public class Ungroup implements ICommand, IUndoable {

    private final UngroupHandler ungroupHandler;

    public Ungroup() {
        this.ungroupHandler = new UngroupHandler();
    }

    @Override
    public void execute() {
        ungroupHandler.execute();
        CommandInvoker.add(this); 
    }

    @Override
    public void undo() {
        ungroupHandler.undo();
    }

    @Override
    public void redo() {
        ungroupHandler.redo();
    }
}
