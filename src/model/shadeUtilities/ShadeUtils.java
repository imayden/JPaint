package model.shadeUtilities;

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

