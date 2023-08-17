package model.colorUtilities;

// Design Pattern Used: Simple Factory

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IApplicationState;
import java.awt.Color;

public class ColorUtils {
    public static Color getPrimaryColor(IApplicationState appState) {
        return ShapeColorFactory.getMap().get(appState.getActivePrimaryColor());
    }

    public static Color getSecondaryColor(IApplicationState appState) {
        return ShapeColorFactory.getMap().get(appState.getActiveSecondaryColor());
    }
}
