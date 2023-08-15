package model.command;

import model.interfaces.IShape;
import model.mouseUtilities.MouseListener;
import model.mouseUtilities.Point;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShape.Ellipse;
import model.shapeUtilities.drawShape.Rectangle;
import model.shapeUtilities.drawShape.Triangle;
import model.shapeUtilities.drawShapeOutline.RectangleOutline;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.awt.*;

public class GroupHandler implements IShape {

    private IPaintCanvas paintCanvas;
    private Graphics2D graphics2d;

    private List<IShape> shapeToGroup = new ArrayList<>();

    private Point startPoint;
    private Point endPoint;
    private int width;
    private int height;
    private String shadeType;
    private Color primaryColor;
    private Color secondaryColor;
    private int startPointX, startPointY;

    public GroupHandler() {
    }

    public GroupHandler(IPaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.graphics2d = paintCanvas.getGraphics2D();
    }

    public GroupHandler(GroupHandler shapeGroup) {

        for (IShape shape : shapeGroup.shapeToGroup) {
            Point startPoint = new Point(shape.getStartPointX(), shape.getStartPointY());
            Point endPoint = new Point(shape.getStartPointX() + shape.getWidth(),
                    shape.getStartPointY() + shape.getHeight());
            switch (shape.getClass().getSimpleName()) {
                case "Rectangle":
                    Rectangle rectShape = (Rectangle) shape;
                    shapeToGroup.add((IShape) new Rectangle(startPoint, endPoint, rectShape.getPaintCanvas(),
                            rectShape.getShadeType(), rectShape.getPrimaryColor(), rectShape.getSecondaryColor()));
                    break;
                case "Ellipse":
                    Ellipse ellipseShape = (Ellipse) shape;
                    shapeToGroup.add((IShape) new Ellipse(startPoint, endPoint, ellipseShape.getPaintCanvas(),
                            ellipseShape.getShadeType(), ellipseShape.getPrimaryColor(),
                            ellipseShape.getSecondaryColor()));
                    break;
                case "Triangle":
                    Triangle triangleShape = (Triangle) shape;
                    int[] xValues = triangleShape.getXCoords();
                    int[] yValues = triangleShape.getYCoords();
                    startPoint = new Point(xValues[0], yValues[0]);
                    endPoint = new Point(xValues[2], yValues[2]);
                    shapeToGroup.add((IShape) new Triangle(startPoint, endPoint, triangleShape.getPaintCanvas(),
                            triangleShape.getShadeType(), triangleShape.getPrimaryColor(),
                            triangleShape.getSecondaryColor()));
                    break;
                case "GroupHandler":
                    shapeToGroup.add((IShape) new GroupHandler((GroupHandler) shape));
                    break;
                default:
                    break;
            }
        }
        width = shapeGroup.width;
        height = shapeGroup.height;
        startPointX = shapeGroup.startPointX;
        startPointY = shapeGroup.startPointY;
        paintCanvas = shapeGroup.paintCanvas;

    }

    public void group() {
        List<IShape> shapeGroupList = activeShape;
        if (shapeGroupList != null) {
            for (IShape shape : shapeGroupList) {
                if (!shapeToGroup.contains(shape)) {
                    shapeToGroup.add(shape);
                }
            }
            for (IShape shape : shapeToGroup) {
                shapeGroupList.remove(shape);
            }

            shapeGroupList.add(this);

            ExistingShape.shapeList.add(this);
            for (IShape shape : shapeToGroup) {
                ExistingShape.shapeList.remove(shape);
            }

            UpdateCanvas.update(MouseListener.getPaintCanvas());
            drawBoundingBox();

        }

        CommandInvoker.add(this);
    }

    public void drawBoundingBox() {
        int xMin = Integer.MAX_VALUE;
        int yMin = Integer.MAX_VALUE;
        int xMax = Integer.MIN_VALUE;
        int yMax = Integer.MIN_VALUE;
        for (IShape shape : shapeToGroup) {

            xMin = Math.min(xMin, shape.getStartPointX());
            yMin = Math.min(yMin, shape.getStartPointY());
            xMax = Math.max(xMax, shape.getStartPointX() + shape.getWidth());
            yMax = Math.max(yMax, shape.getStartPointY() + shape.getHeight());

        }

        width = xMax - xMin;
        height = yMax - yMin;
        startPointX = xMin;
        startPointY = yMin;

        System.out.println("group_xMin: " + xMin);
        System.out.println("group_width: " + (xMax - xMin));
        System.out.println("group_xMax: " + xMax);
        System.out.println("group_height: " + (yMax - yMin));

        RectangleOutline rectangleOutline = new RectangleOutline();
        rectangleOutline.draw(xMin, yMin, xMax - xMin, yMax - yMin, paintCanvas);
    }

    public List<IShape> getShapeToGroup() {
        return this.shapeToGroup;
    }

    public void setShapeToGroup(List<IShape> list) {
        this.shapeToGroup = list;
    }

    @Override
    public void drawShape() {
        for (IShape shape : shapeToGroup) {
            shape.drawShape();
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
        for (IShape shape : shapeToGroup) {
            shape.updateCoordinates(xDelta, yDelta);
        }
    }

    @Override
    public Point getStartPoint() {
        return startPoint;
    }

    @Override
    public Point getEndPoint() {
        return endPoint;
    }

    @Override
    public int getStartPointX() {
        return this.startPointX;
    }

    @Override
    public int getStartPointY() {
        return this.startPointY;
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
        for (IShape shape : shapeToGroup) {
            ExistingShape.shapeList.add(shape);
        }
        ExistingShape.shapeList.remove(this);
        UpdateCanvas.update(paintCanvas);
    }

    @Override
    public void redo() {
        for (IShape shape : shapeToGroup) {
            ExistingShape.shapeList.remove(shape);
        }
        ExistingShape.shapeList.add(this);
        UpdateCanvas.update(paintCanvas);
    }

}
