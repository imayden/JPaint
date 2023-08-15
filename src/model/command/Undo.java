package model.command;

import model.interfaces.ICommand;

public class Undo implements ICommand {
    @Override
    public void execute() {
        CommandInvoker.undo();
    }
}
