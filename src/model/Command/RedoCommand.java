package model.Command;

// ydeng24@depaul.edu

import model.interfaces.ICommand;

public class RedoCommand implements ICommand {
    @Override
    public void run() {
        CommandHistory.redo();
    }
}
