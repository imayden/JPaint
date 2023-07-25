package model;

import java.awt.Color;
import model.interfaces.IApplicationState;

public class ColorHandler {
    static Color getPrimaryColor(IApplicationState appState) {
        return ShapeColor.colorsMap.get(appState.getActivePrimaryColor());
    }

    static Color getSecondaryColor(IApplicationState appState) {
        return ShapeColor.colorsMap.get(appState.getActiveSecondaryColor());
    }
}
