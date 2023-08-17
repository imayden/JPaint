package model.persistence;

// ydeng24@depaul.edu

import model.*;
import model.colorUtilities.ShapeColor;
import model.command.*;
import model.command.CommandHandler.MoveHandler;
import model.command.CommandHandler.PasteHandler;
import model.dialogs.DialogProvider;
import model.interfaces.*;
import model.mouseUtilities.ActiveMouseMode;
import model.mouseUtilities.MouseMode;
import model.shadeUtilities.ShapeShadingType;
import model.shapeUtilities.ShapeType;
import model.shapeUtilities.drawShapeOutline.OutlineHanderProxy;
import view.interfaces.IUiModule;
import java.io.Serializable;

public class ApplicationState implements IApplicationState, Serializable {
    private static final long serialVersionUID = -5545483996576839009L;
    private final IUiModule uiModule;
    private final IDialogProvider dialogProvider;

    private ShapeType activeShapeType;
    private ShapeColor activePrimaryColor;
    private ShapeColor activeSecondaryColor;
    private ShapeShadingType activeShapeShadingType;
    private MouseMode activeMouseMode;
    private ICommand pasteOperation = null;
    private ICommand copyOperation = null;
    private static boolean undoSelected = false;
    private static boolean redoSelected = false;
    OutlineHanderProxy undoShapeProxy = null;
    OutlineHanderProxy redoShapeProxy = null;

    public ApplicationState(IUiModule uiModule) {
        this.uiModule = uiModule;
        this.dialogProvider = new DialogProvider(this);
        setDefaults();
    }

    @Override
    public void setActiveShape() {
        activeShapeType = uiModule.getDialogResponse(dialogProvider.getChooseShapeDialog());
    }

    @Override
    public void setActivePrimaryColor() {
        activePrimaryColor = uiModule.getDialogResponse(dialogProvider.getChoosePrimaryColorDialog());
        System.out.println(activePrimaryColor);
    }

    @Override
    public void setActiveSecondaryColor() {
        activeSecondaryColor = uiModule.getDialogResponse(dialogProvider.getChooseSecondaryColorDialog());
    }

    @Override
    public void setActiveShadingType() {
        activeShapeShadingType = uiModule.getDialogResponse(dialogProvider.getChooseShadingTypeDialog());
    }

    @Override
    public void setActiveMouseMode() {
        activeMouseMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
    }

    @Override
    public ShapeType getActiveShapeType() {
        return activeShapeType;
    }

    @Override
    public ShapeColor getActivePrimaryColor() {
        return activePrimaryColor;
    }

    @Override
    public ShapeColor getActiveSecondaryColor() {
        return activeSecondaryColor;
    }

    @Override
    public ShapeShadingType getActiveShapeShadingType() {
        return activeShapeShadingType;
    }

    @Override
    public MouseMode getActiveMouseMode() {
        return activeMouseMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeMouseMode = MouseMode.DRAW;
    }

    // Checkin 1: Undo, Redo

    @Override
    public void undo()
    {
        Undo undoOperation = new Undo();
        undoOperation.execute();
        // undoSelected = Move.isUndoSelected();
        undoSelected = MoveHandler.isUndoSelected();
        if(undoSelected) {
            undoShapeProxy = new OutlineHanderProxy(undoOperation);
            ActiveMouseMode.printShapeOutline(undoShapeProxy);
        }
        System.out.println("Action: Undo");

    }
    
    @Override
    public void redo() {
        Redo redoOperation = new Redo();
        redoOperation.execute();
        // redoSelected = Move.isRedoSelected();
        redoSelected = MoveHandler.isRedoSelected();
        if(redoSelected) {
            redoShapeProxy = new OutlineHanderProxy(redoOperation);
            ActiveMouseMode.printShapeOutline(redoShapeProxy);
        }
        System.out.println("Action: Redo");
    }

    public static boolean isUndoSelected() {
        return undoSelected;
    }

    public static boolean isRedoSelected() {
        return redoSelected;
    }

    // Checkin 3: Copy, Paste, Delete
    
    @Override
    public void copy() {
        copyOperation =  new Copy();
        copyOperation.execute();
        System.out.println("Action: Copy");
    }

    @Override
    public void paste() {
        // pasteOperation =  new Paste();
        pasteOperation =  new PasteHandler();
        pasteOperation.execute();
        System.out.println("Action: Paste");
    }

    @Override
    public void delete() {
        Delete deleteOperation = new Delete();
        deleteOperation.execute();
        System.out.println("Action: Delete");
    }

    // Checkin 4: Group, Ungroup

    @Override
    public void group() {
        Group groupOperation = new Group();
        groupOperation.execute();
        System.out.println("Action: Group");
    }

    @Override
    public void ungroup() {
        Ungroup ungroupOperation = new Ungroup();
        ungroupOperation.execute();
        System.out.println("Action: Ungroup");
    }

    @Override
    public String toString() {
        return "ApplicationState{" +
                "uiModule=" + uiModule +
                ", dialogProvider=" + dialogProvider +
                ", activeShapeType=" + activeShapeType +
                ", activePrimaryColor=" + activePrimaryColor +
                ", activeSecondaryColor=" + activeSecondaryColor +
                ", activeShapeShadingType=" + activeShapeShadingType +
                ", activeMouseMode=" + activeMouseMode +
                '}';
    }
}
