package pattern.nullObject;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.Point;
import model.ActiveShape;
import model.interfaces.IShape;
import view.gui.PaintCanvas;
import view.interfaces.IPaintCanvas;
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
    public IPaintCanvas getPaintCanvas() {
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
    public IShape getDrawShapeStrategy() {
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
        ExistingShape.shapeList.remove(this);
        if (ActiveShape.activeShape.contains(this))
            ActiveShape.activeShape.remove(this);
    }

    @Override
    public void redo() {
        ExistingShape.shapeList.add(this);
    }

}
