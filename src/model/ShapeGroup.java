package model;

import model.ExistingShape;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import model.interfaces.IActiveShape;
import pattern.command.interfaces.IUndoable;
import pattern.factory.interfaces.IShape;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.awt.*;

public class ShapeGroup implements IShape, IActiveShape {  
  
  private IPaintCanvas paintCanvas;
  private Graphics2D graphics2d;

  private Shape groupOutline;
  private List<IShape> shapeToGroup = new ArrayList<>();


  // TODO
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


    public ShapeGroup(IPaintCanvas paintCanvas) {
        this.paintCanvas = paintCanvas;
        this.graphics2d = paintCanvas.getGraphics2D();
    }


  public void group() {
    List<IShape> shapeGroupList = activeShape;
    if(shapeGroupList != null){
        for(IShape shape: shapeGroupList){
            if (!shapeToGroup.contains(shape)) {
                shapeToGroup.add(shape);

                // TODO
                
            }
        }
        shapeGroupList.remove(shapeToGroup);
        shapeGroupList.add(this);

        // ExistingShape.shapeList.remove(shapeToGroup);
        // or
        ExistingShape.shapeList.add(this);
        for (IShape shape: shapeToGroup) {
          ExistingShape.shapeList.remove(shape);
        }

        drawOutline();

    }
  }

  public void drawOutline() {
    int xMin = Integer.MAX_VALUE;
    int yMin = Integer.MAX_VALUE;
    int xMax = Integer.MIN_VALUE;
    int yMax = Integer.MIN_VALUE;
    for (IShape shape : shapeToGroup) {
// getStartPointX();
//     public int getStartPointY();
//     public int getWidth();
//     public int getHeight();


      xMin = Math.min(xMin,shape.getStartPointX());
      yMin = Math.min(yMin,shape.getStartPointY());
      xMax = Math.max(xMax,shape.getStartPointX() + shape.getWidth());
      yMax = Math.max(yMax,shape.getStartPointY() + shape.getHeight());

    }

    // startPoint = new Point(xMin, yMin);
    // endPoint = new Point(xMax, yMax);
    width = xMax - xMin;
    height = yMax - yMin;
    startPointX = xMin;
    startPointY = yMin;


    // TODO Math.abs()
    Rectangle rect = new Rectangle(xMin,yMin,xMax - xMin, yMax - yMin);
    System.out.println("xMin: " + xMin);
    System.out.println("width: " + (xMax - xMin));
    System.out.println("xMax: " + xMax);
    System.out.println("height: " + (yMax - yMin));
    graphics2d.draw(rect);
  }

  public List<IShape> getShapeToGroup () {
    return this.shapeToGroup;
  }

  @Override
    public void drawShape() {
        for (IShape shape: shapeToGroup) {
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
        for (IShape shape: shapeToGroup) {
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

