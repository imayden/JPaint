package model.shapeUtilities.drawShape;

// Design Pattern Used: Template Method, Factory, State, Command, Adapter, Strategy

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IShape;
import model.mouseUtilities.Point;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import view.gui.APaintCanvas;
import view.gui.UpdateCanvas;

import java.awt.*;
import java.util.LinkedList;

public class Ellipse implements IShape {
    private APaintCanvas paintCanvas;
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
    

    public Ellipse(Point startPoint, Point endPoint, APaintCanvas paintCanvas,
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

    public Ellipse(IShape shape) {
        this(shape.getStartPoint(), shape.getEndPoint(), shape.getPaintCanvas(),
             shape.getShadeType(), shape.getPrimaryColor(), shape.getSecondaryColor());
    }

    @Override
    public void drawShape() {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        switch (shadeType) {
            case "filled":
                graphics2d.setColor(primaryColor);
                graphics2d.fillOval(startPointX, startPointY, width, height);
                break;
            case "outline":
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(primaryColor);
                graphics2d.drawOval(startPointX, startPointY, width, height);
                break;
            case "filledAndOutline":
                graphics2d.setColor(primaryColor);
                graphics2d.fillOval(startPointX, startPointY, width, height);
                graphics2d.setStroke(new BasicStroke(5));
                graphics2d.setColor(secondaryColor);
                graphics2d.drawOval(startPointX, startPointY, width, height);
                break;
            default:
                break;
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
    public APaintCanvas getPaintCanvas() {
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

}