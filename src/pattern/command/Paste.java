package pattern.command;

import pattern.command.interfaces.IUndoable;
import pattern.factory.interfaces.IShape;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

public class Paste implements IUndoable {
    private LinkedList<IShape> shapetoPaste;
    private IPaintCanvas paintCanvas;
    private static boolean isPasteSelected = false;
    private PasteHandler handler;

    public Paste() {
        this.shapetoPaste = new LinkedList<>();
        this.handler = new PasteHandler();
        this.paintCanvas = handler.getPaintCanvas();
    }

    public void execute() {
        handler.execute();
    }

    @Override
    public void undo() {
        handler.undo();
    }

    @Override
    public void redo() {
        handler.redo();
    }


}

