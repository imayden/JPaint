package model.shapeUtilities.nullObject;

// Design Pattern Used: Null Object

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IShape;
import model.mouseUtilities.Point;
import view.gui.APaintCanvas;
import view.gui.PaintCanvas;

import java.awt.*;
import java.util.LinkedList;

public class NullShape implements IShape {

    @Override
    public void drawShape() {
        System.out.println("Null Shape!");
    }

    @Override
    public LinkedList<IShape> getShapeChildren() {
        return null;
    }

    @Override
    public void updateCoordinates(int xDelta, int yDelta) {
        System.out.println("Null Shape!");
    }

    @Override
    public APaintCanvas getPaintCanvas() {
        return new PaintCanvas();
    }

    @Override
    public int getWidth() {
        return 0;
    }

    @Override
    public int getHeight() {
        return 0;
    }

    @Override
    public Point getStartPoint() {
        return null;
    }

    @Override
    public Point getEndPoint() {
        return null;
    }

    @Override
    public int getStartPointX() {
        return 0;
    }

    @Override
    public int getStartPointY() {
        return 0;
    }

    @Override
    public IShape getShapeDrawingStrategy() {
        return null;
    }

    @Override
    public String getShadeType() {
        return "filled";
    }

    @Override
    public Color getPrimaryColor() {
        return Color.BLACK;
    }

    @Override
    public Color getSecondaryColor() {
        return Color.BLACK;
    }

    @Override
    public void undo() {
        System.out.println("Null Shape!");
    }

    @Override
    public void redo() {
        System.out.println("Null Shape!");
    }

}
