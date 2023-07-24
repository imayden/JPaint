package command;

// ydeng24@depaul.edu

import model.interfaces.IUndoable;
import java.util.Stack;

public class CommandInvoker {
    private static final Stack<IUndoable> undoStack = new Stack<IUndoable>();
    private static final Stack<IUndoable> redoStack = new Stack<IUndoable>();

    public static void add(IUndoable cmd) {
        undoStack.push(cmd);
        redoStack.clear();
    }

    public static boolean undo() {
        boolean result = !undoStack.empty();
        if (result) {
            IUndoable c = undoStack.pop();
            c.undo();
            redoStack.push(c);
        }
        return result;
    }

    public static boolean redo() {
        boolean result = !redoStack.empty();
        if (result) {
            IUndoable c = redoStack.pop();
            c.redo();
            undoStack.push(c);
        }
        return result;
    }
}
