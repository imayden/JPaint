package pattern.proxy;

import model.persistence.ApplicationState;
import pattern.command.MoveHandler;
import pattern.command.SelectHandler;
import pattern.command.interfaces.ICommand;
import pattern.proxy.interfaces.IOutlineHandler;

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

