package model;

// ydeng24@depaul.edu

import java.awt.*;
import java.util.EnumMap;


public enum ShapeColor {
    BLACK,
    BLUE,
    CYAN,
    DARK_GRAY,
    GRAY,
    GREEN,
    LIGHT_GRAY,
    MAGENTA,
    ORANGE,
    PINK,
    RED,
    WHITE,
    YELLOW;

    public static EnumMap<ShapeColor, Color> colorsMap = new EnumMap<>(ShapeColor.class);

    // Map colors for shape drawing
    public static void setup() {
        colorsMap.put(ShapeColor.LIGHT_GRAY, Color.LIGHT_GRAY);
        colorsMap.put(ShapeColor.MAGENTA, Color.MAGENTA);
        colorsMap.put(ShapeColor.ORANGE, Color.ORANGE);
        colorsMap.put(ShapeColor.WHITE, Color.WHITE);
        colorsMap.put(ShapeColor.YELLOW, Color.YELLOW);
        colorsMap.put(ShapeColor.BLACK, Color.BLACK);
        colorsMap.put(ShapeColor.BLUE, Color.BLUE);
        colorsMap.put(ShapeColor.CYAN, Color.CYAN);
        colorsMap.put(ShapeColor.PINK, Color.PINK);
        colorsMap.put(ShapeColor.RED, Color.RED);
        colorsMap.put(ShapeColor.DARK_GRAY, Color.DARK_GRAY);
        colorsMap.put(ShapeColor.GRAY, Color.GRAY);
        colorsMap.put(ShapeColor.GREEN, Color.GREEN);
    }

    public static EnumMap getMap() {
        setup();
        return colorsMap;
    }
}

