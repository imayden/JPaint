package controller;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import command.Action;
import command.ClearAction;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Stack;

public class DrawingController {

    private ArrayList<Rectangle> rectangles;
    private Stack<Action> undoStack;
    private Stack<Action> redoStack;

    public DrawingController(ArrayList<Rectangle> rectangles, Stack<Action> undoStack, Stack<Action> redoStack) {
        this.rectangles = rectangles;
        this.undoStack = undoStack;
        this.redoStack = redoStack;
    }

    public void undo() {
        if (!undoStack.isEmpty()) {
            Action action = undoStack.pop();
            action.undo();
            redoStack.push(action);
        }
    }

    public void redo() {
        if (!redoStack.isEmpty()) {
            Action action = redoStack.pop();
            action.redo();
            undoStack.push(action);
        }
    }

    public void clear() {
        if (!rectangles.isEmpty()) {
            ClearAction clearAction = new ClearAction(rectangles);
            clearAction.redo();
            undoStack.push(clearAction);
            rectangles.clear();
        }
    }

    public void repaint() {
    }
}
