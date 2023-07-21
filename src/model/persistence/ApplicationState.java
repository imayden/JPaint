package model.persistence;

// ydeng24@depaul.edu

import model.*;
import model.Command.*;
import model.Proxy.SelectedOutlineProxy;
import model.dialogs.DialogProvider;
import model.interfaces.*;
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
    private StartAndEndPointMode activeStartAndEndPointMode;
    private ICommand pasteOperation = null;
    private ICommand copyOperation = null;
    private static boolean undoSelected = false;
    private static boolean redoSelected = false;
    SelectedOutlineProxy undoShapeProxy = null;
    SelectedOutlineProxy redoShapeProxy = null;

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
    public StartAndEndPointMode getActiveStartAndEndPointMode() {
        return activeStartAndEndPointMode;
    }

    private void setDefaults() {
        activeShapeType = ShapeType.RECTANGLE;
        activePrimaryColor = ShapeColor.BLUE;
        activeSecondaryColor = ShapeColor.GREEN;
        activeShapeShadingType = ShapeShadingType.FILLED_IN;
        activeStartAndEndPointMode = StartAndEndPointMode.DRAW;
    }

    // Checkin 1/2: Undo, Redo, Select, Move

    @Override
    public void undoCommand()
    {
        UndoCommand undoOperation = new UndoCommand();
        undoOperation.run();
        undoSelected = MoveCommand.isUndoSelected();
        if(undoSelected) {
            undoShapeProxy = new SelectedOutlineProxy(undoOperation);
            SelectModeOption.printShapeOutline(undoShapeProxy);
        }

    }
    
    @Override
    public void redoCommand() {
        RedoCommand redoOperation = new RedoCommand();
        redoOperation.run();
        redoSelected = MoveCommand.isRedoSelected();
        if(redoSelected) {
            redoShapeProxy = new SelectedOutlineProxy(redoOperation);
            SelectModeOption.printShapeOutline(redoShapeProxy);
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
    public void copyCommand() {
        copyOperation =  new CopyCommand();
        copyOperation.run();
    }

    @Override
    public void pasteCommand() {
        pasteOperation =  new PasteCommand();
        pasteOperation.run();
    }

    @Override
    public void deleteCommand() {
        DeleteCommand deleteOperation = new DeleteCommand();
        deleteOperation.run();
    }

    // Checkin 4: Group, Ungroup

    // @Override
    // public void groupCommand() {}

    // @Override
    // public void ungroupCommand() {}

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
