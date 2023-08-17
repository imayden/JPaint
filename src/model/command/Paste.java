package model.command;

import java.util.LinkedList;
import model.command.CommandHandler.PasteHandler;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.APaintCanvas;

public class Paste implements IUndoable {
    private LinkedList<IShape> shapetoPaste;
    private APaintCanvas paintCanvas;
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
