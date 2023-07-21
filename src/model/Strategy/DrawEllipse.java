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

// Draw ellipse
public class DrawEllipse implements IShape {
    private IPaintCanvas paintCanvas;
    private Point startPoint;
    private Point endPoint;
    private Point minimum;
    private Point maximum;
    private int width;
    private int height;
    private String shadeType;
    private Color primaryColor;
    private Color secondaryColor;
    private int startPointX, startPointY;

    public DrawEllipse(Point startPoint, Point endPoint, IPaintCanvas paintCanvas,
                            String shadeType, Color primaryColor, Color secondaryColor) {
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.paintCanvas = paintCanvas;
        this.shadeType = shadeType;
        this.primaryColor = primaryColor;
        this.secondaryColor = secondaryColor;

        minimum = new Point(Math.min(startPoint.getX(), endPoint.getX()),
                Math.min(startPoint.getY(), endPoint.getY()));
        maximum = new Point(Math.max(startPoint.getX(), endPoint.getX()),
                Math.max(startPoint.getY(), endPoint.getY()));
        width = Math.abs(maximum.getX() - minimum.getX());
        height = Math.abs(maximum.getY() - minimum.getY());

        startPointX = minimum.getX();
        startPointY = minimum.getY();
    }

    @Override
    public void drawShape() {

        // Set shading type
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        if (shadeType.equals("filled")) {
            graphics2d.setColor(primaryColor);
            graphics2d.fillOval(startPointX, startPointY, width, height);
        } else if (shadeType == "outline") {
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(primaryColor);
            graphics2d.drawOval(startPointX, startPointY, width, height);
        } else if (shadeType == "filledAndOutline") {
            graphics2d.setColor(primaryColor);
            graphics2d.fillOval(startPointX, startPointY, width, height);
            graphics2d.setStroke(new BasicStroke(5));
            graphics2d.setColor(secondaryColor);
            graphics2d.drawOval(startPointX, startPointY, width, height);
        }
    }

    @Override
    public LinkedList<IShape> getShapeChildren() {
        return null;
    }

    @Override
    public void updateCoordinates(int xDelta, int yDelta) {
        startPointX = startPointX + xDelta;
        startPointY = startPointY + yDelta;
    }

    @Override
    public int getStartPointX() {
        return startPointX;
    }

    @Override
    public int getStartPointY() {
        return startPointY;
    }

    @Override
    public IPaintCanvas getPaintCanvas() {
        return this.paintCanvas;
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
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