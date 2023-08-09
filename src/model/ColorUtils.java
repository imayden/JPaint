package model;


import model.interfaces.IApplicationState;
import pattern.flyweight.ShapeColorFactory;

import java.awt.Color;

public class ColorUtils {
    public static Color getPrimaryColor(IApplicationState appState) {
        return ShapeColorFactory.getMap().get(appState.getActivePrimaryColor());
    }

    public static Color getSecondaryColor(IApplicationState appState) {
        return ShapeColorFactory.getMap().get(appState.getActiveSecondaryColor());
    }
}
