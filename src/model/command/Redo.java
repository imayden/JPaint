package model.command;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.ICommand;

public class Redo implements ICommand {
    @Override
    public void execute() {
        CommandInvoker.redo();
    }
}
