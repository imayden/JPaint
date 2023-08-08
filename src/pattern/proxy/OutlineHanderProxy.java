package pattern.proxy;

import model.persistence.ApplicationState;
import pattern.command.Move;
import pattern.command.Select;
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
            case "Select":
                performOutlineIfConditionMet(Select.isSelected());
                break;
            case "Move":
                performOutlineIfConditionMet(Move.isMoveSelected());
                break;
            case "ApplicationState":
                performOutlineIfConditionMet(ApplicationState.isUndoSelected() || ApplicationState.isRedoSelected());
                break;
            default:
                System.out.println("Other actions");
        }
    }

    private void performOutlineIfConditionMet(boolean condition) {
        if (condition) {
            activeShapeOutline = new OutlineHandler();
            activeShapeOutline.outline();
        }
    }
}

