package model.Command;

// ydeng24@depaul.edu

import model.interfaces.ICommand;
import model.interfaces.IClipboardCommand;
import model.interfaces.ISelectedShapesList;
import model.interfaces.IShape;

// Copy selected shape to clipboard
public class CopyCommand implements ICommand, IClipboardCommand, ISelectedShapesList {

    @Override
    public void run() {
        clipboardShapes.clear();
        for (IShape temp : selectedShapes) {
            clipboardShapes.add(temp);
        }
        System.out.println("Copied shape: " + clipboardShapes.toString() + "!");
    }
}
