package command;

// ydeng24@depaul.edu

import model.interfaces.ICommand;

public class UndoCommand implements ICommand {
    @Override
    public void run() {
        CommandInvoker.undo();
    }
}
