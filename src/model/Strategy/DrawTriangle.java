package model.Strategy;

// ydeng24@depaul.edu

import model.DrawnShapesList;
import model.Point;
import model.SelectedShapesList;
import model.interfaces.IShape;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.awt.*;
import java.util.LinkedList;

// Draw triangle
public class DrawTriangle implements IShape {

    private IPaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private int[] xValues = new int[3];
    private int[] yValues = new int[3];
    private String shadeType;
    private Color primaryColor;
    private Color secondaryColor;

    public DrawTriangle(Point startPoint, Point endPoint, IPaintCanvas paintCanvas,
                             String shadeType, Color primaryColor, Color secondaryColor) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.shadeType = shadeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

        //Set x's coordinate
        xValues[0] = startPoint.getX();
        xValues[1] = startPoint.getX();
        xValues[2] = endPoint.getX();

        //Set y's oordinate
        yValues[0] = startPoint.getY();
        yValues[1] = endPoint.getY();
        yValues[2] = endPoint.getY();
    }

    @Override
    public void drawShape() {

        // Set shading type
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        if (shadeType.equals("filled")) {
            graphics2d.setColor(primaryColor);
            graphics2d.fillPolygon(xValues, yValues, 3);
        } else if (shadeType == "outline") {
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(primaryColor);
            graphics2d.drawPolygon(xValues, yValues, 3);
        } else if (shadeType == "filledAndOutline") {
            graphics2d.setColor(primaryColor);
            graphics2d.fillPolygon(xValues, yValues, 3);
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(secondaryColor);
            graphics2d.drawPolygon(xValues, yValues, 3);
        }
    }

    @Override
    public LinkedList<IShape> getShapeChildren() {
        return null;
    }

    @Override
    public void updateCoordinates(int xDelta, int yDelta) {
        xValues[0] = xValues[0] + xDelta;
        xValues[1] = xValues[1] + xDelta;
        xValues[2] = xValues[2] + xDelta;
        yValues[0] = yValues[0] + yDelta;
        yValues[1] = yValues[1] + yDelta;
        yValues[2] = yValues[2] + yDelta;
    }

    public int[] getXValues() {
        int[] tempX = new int[3];
        for (int i = 0; i < 3; i++)
            tempX[i] = xValues[i];
        return tempX;
    }

    public int[] getYValues() {
        int[] tempY = new int[3];
        for (int i = 0; i < 3; i++)
            tempY[i] = yValues[i];
        return yValues;
    }

    @Override
    public int getStartPointX() {
        if (xValues[0] < xValues[2]) return xValues[0];
        else return xValues[2];
    }

    @Override
    public int getStartPointY() {
        if (yValues[0] < yValues[2]) return yValues[0];
        else return yValues[2];
    }

    @Override
    public IPaintCanvas getPaintCanvas() {
        return this.paintCanvas;
    }

    @Override
    public int getWidth() {
        return java.lang.Math.abs(xValues[0] - xValues[2]);
    }

    @Override
    public int getHeight() {
        return java.lang.Math.abs(yValues[0] - yValues[2]);
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
    public IShape getDrawShapeStrategy() {
        return getDrawShapeStrategy();
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
        DrawnShapesList.shapeList.removeLast();
        if (SelectedShapesList.selectedShapes.contains(this))
            SelectedShapesList.selectedShapes.removeLast();
        UpdateCanvas.update(paintCanvas);
    }

    @Override
    public void redo() {
        DrawnShapesList.shapeList.add(this);
        UpdateCanvas.update(paintCanvas);
    }

}