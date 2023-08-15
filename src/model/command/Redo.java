package model.command;

import model.interfaces.ICommand;

public class Redo implements ICommand {
    @Override
    public void execute() {
        CommandInvoker.redo();
    }
}
