package pattern.command;

import pattern.command.interfaces.ICommand;

public class Undo implements ICommand {
    @Override
    public void execute() {
        CommandInvoker.undo();
    }
}
