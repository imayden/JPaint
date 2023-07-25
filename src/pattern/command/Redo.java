package pattern.command;

import pattern.command.interfaces.ICommand;

public class Redo implements ICommand {
    @Override
    public void execute() {
        CommandInvoker.redo();
    }
}
