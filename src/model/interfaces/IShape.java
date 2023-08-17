package model.interfaces;

import view.interfaces.APaintCanvas;
import java.awt.*;
import java.util.LinkedList;

import model.mouseUtilities.Point;

// ydeng24@depaul.edu

public interface IShape extends IUndoable, IActiveShape

{
    public int getStartPointX();
    public int getStartPointY();
    public int getWidth();
    public int getHeight();
    public APaintCanvas getPaintCanvas();
    public Point getStartPoint();
    public Point getEndPoint();
    public IShape getShapeDrawingStrategy();
    public String getShadeType();
    public Color getPrimaryColor();
    public Color getSecondaryColor();
    public void drawShape();
    public void updateCoordinates(int xDelta, int yDelta);
    public LinkedList<IShape> getShapeChildren();
}
