package model.shapeUtilities.drawShapeOutline;

// Design Pattern Used: Proxy

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

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
        if (cmd instanceof SelectHandler) {
            drawOutlineIf(SelectHandler.isSelected());
            System.out.println("Action: Select");
        } else if (cmd instanceof MoveHandler) {
            drawOutlineIf(MoveHandler.isMoveSelected());
            System.out.println("Action: Move");
        } else if (cmd instanceof ApplicationState) {
            drawOutlineIf(ApplicationState.isUndoSelected() || ApplicationState.isRedoSelected());
        }
    }

    private void drawOutlineIf(boolean condition) {
        if (condition) {
            activeShapeOutline = new OutlineHandler();
            activeShapeOutline.outline();
        }
    }
}

