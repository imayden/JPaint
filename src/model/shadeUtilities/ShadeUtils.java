package model.shadeUtilities;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IApplicationState;

public class ShadeUtils {
    public static String getShadingType(IApplicationState appState) {
        switch (appState.getActiveShapeShadingType()) {
            case FILLED_IN:
                return "filled";
            case OUTLINE:
                return "outline";
            case OUTLINE_AND_FILLED_IN:
                return "filledAndOutline";
            default:
                return null;
        }
    }
}

