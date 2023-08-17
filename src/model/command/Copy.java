package model.command;

// Design Pattern Used: Command

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IClipboard;
import model.interfaces.ICommand;
import model.interfaces.IActiveShape;

public class Copy implements ICommand, IClipboard, IActiveShape {

    @Override
    public void execute() {
        clipboard.clear();
        activeShape.forEach(shape -> clipboard.add(shape));
        System.out.println("Shapes on the clipboard: " + clipboard.size());
    }
}
