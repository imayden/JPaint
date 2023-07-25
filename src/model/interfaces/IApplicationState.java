package model.interfaces;

// ydeng24@depaul.edu

import model.ShapeColor;
import model.ShapeShadingType;
import model.ShapeType;
import model.MouseMode;

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

    MouseMode getActiveStartAndEndPointMode();

    // Checkin 1: Undo, Redo
    void undo();
    void redo();

    // Checkin 3: Copy, Paste, Delte
    void copy();
    void paste();
    void delete();

    // Checkin 4: Group, Ungroup
    // void groupCommand();
    // void ungroupCommand();
}
