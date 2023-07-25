package model.persistence;

// ydeng24@depaul.edu

import model.*;
import model.dialogs.DialogProvider;
import model.interfaces.*;
import pattern.command.*;
import pattern.command.interfaces.ICommand;
import pattern.proxy.OutlineHanderProxy;
import pattern.proxy.OutlineHandler;
import pattern.proxy.interfaces.IOutlineHandler;
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
    private MouseMode activeStartAndEndPointMode;
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
    public void setActiveStartAndEndPointMode() {
        activeStartAndEndPointMode = uiModule.getDialogResponse(dialogProvider.getChooseStartAndEndPointModeDialog());
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
    public MouseMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = MouseMode.DRAW;
    }

    // Checkin 1/2: Undo, Redo, Select, Move

    @Override
    public void undo()
    {
        Undo undoOperation = new Undo();
        undoOperation.execute();
        undoSelected = Move.isUndoSelected();
        if(undoSelected) {
            undoShapeProxy = new OutlineHanderProxy(undoOperation);
            SelectMouseMode.printShapeOutline(undoShapeProxy);
        }

    }
    
    @Override
    public void redo() {
        Redo redoOperation = new Redo();
        redoOperation.execute();
        redoSelected = Move.isRedoSelected();
        if(redoSelected) {
            redoShapeProxy = new OutlineHanderProxy(redoOperation);
            SelectMouseMode.printShapeOutline(redoShapeProxy);
        }
    }

    public static boolean isUndoSelected() {
        return undoSelected;
    }

    public static boolean isRedoSelected() {
        return redoSelected;
    }

    // Checkin 3: Copy, Paste, Delte
    
    @Override
    public void copy() {
        copyOperation =  new Copy();
        copyOperation.execute();
    }

    @Override
    public void paste() {
        pasteOperation =  new Paste();
        pasteOperation.execute();
    }

    @Override
    public void delete() {
        Delete deleteOperation = new Delete();
        deleteOperation.run();
    }

    // Checkin 4: Group, Ungroup

    // @Override
    // public void group() {}

    // @Override
    // public void ungroup() {}

    @Override
    public String toString() {
        return "ApplicationState{" +
                "uiModule=" + uiModule +
                ", dialogProvider=" + dialogProvider +
                ", activeShapeType=" + activeShapeType +
                ", activePrimaryColor=" + activePrimaryColor +
                ", activeSecondaryColor=" + activeSecondaryColor +
                ", activeShapeShadingType=" + activeShapeShadingType +
                ", activeStartAndEndPointMode=" + activeStartAndEndPointMode +
                '}';
    }
}
