package model.shapeUtilities.drawShape;

import model.interfaces.IShape;
import model.mouseUtilities.Point;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import view.gui.UpdateCanvas;
import view.interfaces.APaintCanvas;
import java.awt.*;
import java.util.LinkedList;

public class Triangle implements IShape {

    private APaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private int[] xCoords = new int[3];
    private int[] yCoords = new int[3];
    private String shadeType;
    private Color primaryColor;
    private Color secondaryColor;

    public Triangle(Point startPoint, Point endPoint, APaintCanvas paintCanvas, String shadeType, Color primaryColor, Color secondaryColor) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.shadeType = shadeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

        xCoords[0] = startPoint.getX();
        xCoords[1] = startPoint.getX();
        xCoords[2] = endPoint.getX();

        yCoords[0] = startPoint.getY();
        yCoords[1] = endPoint.getY();
        yCoords[2] = endPoint.getY();
    }

    @Override
    public void drawShape() {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        switch (shadeType) {
            case "filled":
                graphics2d.setColor(primaryColor);
                graphics2d.fillPolygon(xCoords, yCoords, 3);
                break;
            case "outline":
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(primaryColor);
                graphics2d.drawPolygon(xCoords, yCoords, 3);
                break;
            case "filledAndOutline":
                graphics2d.setColor(primaryColor);
                graphics2d.fillPolygon(xCoords, yCoords, 3);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(secondaryColor);
                graphics2d.drawPolygon(xCoords, yCoords, 3);
                break;
            default:
                break;
        }
    }


    public int[] getXCoords() {
        int[] copiedXValues = new int[3];
        System.arraycopy(xCoords, 0, copiedXValues, 0, 3);
        return copiedXValues;
    }

    public int[] getYCoords() {
        int[] copiedYValues = new int[3];
        System.arraycopy(yCoords, 0, copiedYValues, 0, 3);
        return copiedYValues;
    }

    @Override
    public int getStartPointX() {
        if (xCoords[0] < xCoords[2]) return xCoords[0];
        else return xCoords[2];
    }

    @Override
    public int getStartPointY() {
        if (yCoords[0] < yCoords[2]) return yCoords[0];
        else return yCoords[2];
    }

    @Override
    public APaintCanvas getPaintCanvas() {
        return this.paintCanvas;
    }

    @Override
    public int getWidth() {
        return java.lang.Math.abs(xCoords[0] - xCoords[2]);
    }

    @Override
    public int getHeight() {
        return java.lang.Math.abs(yCoords[0] - yCoords[2]);
    }

    @Override
    public Point getStartPoint() {
        return this.startPoint;
    }

    @Override
    public Point getEndPoint() {
        return this.endPoint;
    }

    @Override
    public IShape getShapeDrawingStrategy() {
        return getShapeDrawingStrategy();
    }

    @Override
    public String getShadeType() {
        return this.shadeType;
    }

    @Override
    public Color getPrimaryColor() {
        return this.primaryColor;
    }

    @Override
    public Color getSecondaryColor() {
        return this.secondaryColor;
    }

    @Override
    public void undo() {
        ExistingShape.shapeList.removeLast();
        if (ActiveShape.activeShape.contains(this))
            ActiveShape.activeShape.removeLast();
        UpdateCanvas.update(paintCanvas);
    }

    @Override
    public void redo() {
        ExistingShape.shapeList.add(this);
        UpdateCanvas.update(paintCanvas);
    }

    @Override
    public LinkedList<IShape> getShapeChildren() {
        return null;
    }

    @Override
    public void updateCoordinates(int xDelta, int yDelta) {
        xCoords[0] = xCoords[0] + xDelta;
        xCoords[1] = xCoords[1] + xDelta;
        xCoords[2] = xCoords[2] + xDelta;
        yCoords[0] = yCoords[0] + yDelta;
        yCoords[1] = yCoords[1] + yDelta;
        yCoords[2] = yCoords[2] + yDelta;
    }

}

