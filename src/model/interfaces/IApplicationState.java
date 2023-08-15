package model.interfaces;

import model.colorUtilities.ShapeColor;
import model.mouseUtilities.MouseMode;
import model.shadeUtilities.ShapeShadingType;
import model.shapeUtilities.ShapeType;

public interface IApplicationState {

    void setActiveShape();

    void setActivePrimaryColor();

    void setActiveSecondaryColor();

    void setActiveShadingType();

    void setActiveMouseMode();

    ShapeType getActiveShapeType();

    ShapeColor getActivePrimaryColor();

    ShapeColor getActiveSecondaryColor();

    ShapeShadingType getActiveShapeShadingType();

    MouseMode getActiveMouseMode();

    // Checkin 1: Undo, Redo
    void undo();
    void redo();

    // Checkin 3: Copy, Paste, Delete
    void copy();
    void paste();
    void delete();

    // Checkin 4: Group, Ungroup
    void group();
    void ungroup();
}
