package model.command;

// Design Pattern Used: Command

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandHandler.GroupHandler;
import model.interfaces.ICommand;
import model.interfaces.IUndoable;
import model.mouseUtilities.MouseListener;

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
