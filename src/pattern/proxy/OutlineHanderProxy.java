package pattern.proxy;

import model.persistence.ApplicationState;
import pattern.command.Move;
import pattern.command.Select;
import pattern.command.interfaces.ICommand;
import pattern.proxy.interfaces.IOutlineHandler;

public class OutlineHanderProxy implements IOutlineHandler {
    private ICommand command;
    private OutlineHandler selectedShapeOutline;

    public OutlineHanderProxy(ICommand inputCommand) {
        this.command = inputCommand;
    }

    @Override
    public void shapeOutline() {
        switch(command.getClass().getSimpleName()) {
            case "Select":
                performOutlineIfConditionMet(((Select) command).isSelected());
                break;
            case "Move":
                performOutlineIfConditionMet(((Move) command).isMoveSelected());
                break;
            case "ApplicationState":
                performOutlineIfConditionMet(((ApplicationState) command).isUndoSelected() || ((ApplicationState) command).isRedoSelected());
                break;
            default:
                System.out.println("Other commands");
        }
    }

    private void performOutlineIfConditionMet(boolean condition) {
        if (condition) {
            selectedShapeOutline = new OutlineHandler();
            selectedShapeOutline.shapeOutline();
        }
    }
}

