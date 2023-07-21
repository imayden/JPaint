package model.interfaces;

// ydeng24@depaul.edu

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.StartAndEndPointMode;

public interface IApplicationState {

    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveStartAndEndPointMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    StartAndEndPointMode getActiveStartAndEndPointMode();

    // Checkin 1: Undo, Redo
    void undoCommand();
    void redoCommand();

    // Checkin 3: Copy, Paste, Delte
    void copyCommand();
    void pasteCommand();
    void deleteCommand();

    // Checkin 4: Group, Ungroup
    // void groupCommand();
    // void ungroupCommand();
}
