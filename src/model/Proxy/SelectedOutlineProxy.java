package model.Proxy;

import command.MoveCommand;
import command.SelectCommand;
import model.interfaces.ICommand;
import model.interfaces.ISelectedShapeOutline;
import model.persistence.ApplicationState;

public class SelectedOutlineProxy implements ISelectedShapeOutline {
    private ICommand command;
    private SelectedShapeOutline selectedShapeOutline;

    public SelectedOutlineProxy(ICommand inputCommand) {
        this.command = inputCommand;
    }

    @Override
    public void shapeOutline() {
        switch(command.getClass().getSimpleName()) {
            case "SelectCommand":
                performOutlineIfConditionMet(((SelectCommand) command).isSelected());
                break;
            case "MoveCommand":
                performOutlineIfConditionMet(((MoveCommand) command).isMoveSelected());
                break;
            case "ApplicationState":
                performOutlineIfConditionMet(((ApplicationState) command).isUndoSelected() || ((ApplicationState) command).isRedoSelected());
                break;
            default:
                System.out.println("Unsupported command type");
        }
    }

    private void performOutlineIfConditionMet(boolean condition) {
        if (condition) {
            selectedShapeOutline = new SelectedShapeOutline();
            selectedShapeOutline.shapeOutline();
        }
    }
}

