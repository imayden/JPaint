package model.shapeUtilities.drawShapeOutline;

import model.command.CommandHandler.MoveHandler;
import model.command.CommandHandler.SelectHandler;
import model.interfaces.ICommand;
import model.interfaces.IOutlineHandler;
import model.persistence.ApplicationState;

public class OutlineHanderProxy implements IOutlineHandler {
    private ICommand cmd;
    private OutlineHandler activeShapeOutline;

    public OutlineHanderProxy(ICommand command) {
        this.cmd = command;
    }

    @Override
    public void outline() {
        switch(cmd.getClass().getSimpleName()) {
            // case "Select":
            case "SelectHandler":
                // drawOutlineIf(Select.isSelected());
                drawOutlineIf(SelectHandler.isSelected());
                break;
            // case "Move":
            case "MoveHandler":
                // drawOutlineIf(Move.isMoveSelected());
                drawOutlineIf(MoveHandler.isMoveSelected());
                break;
            case "ApplicationState":
                drawOutlineIf(ApplicationState.isUndoSelected() || ApplicationState.isRedoSelected());
                break;
            default:
                System.out.println("Other actions");
        }
    }

    private void drawOutlineIf(boolean condition) {
        if (condition) {
            activeShapeOutline = new OutlineHandler();
            activeShapeOutline.outline();
        }
    }
}

