package model.command.CommandHandler;

import model.command.CommandInvoker;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IClipboard;
import model.interfaces.ICommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import model.mouseUtilities.Point;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShape.*;
import view.gui.UpdateCanvas;
import view.interfaces.APaintCanvas;
import java.util.LinkedList;

public class PasteHandler implements IClipboard, ICommand, IUndoable {

    private LinkedList<IShape> shapetoPaste;
    private APaintCanvas paintCanvas = null;
    private static boolean isPasteSelected = false;

    public PasteHandler() {
        shapetoPaste = new LinkedList<>();
        
        System.out.println("Shapes on the clipboard: " + clipboard.size());
        for (IShape shape : clipboard) {
            System.out.println(shape.getClass().getSimpleName());
        }
        
        for (IShape shape : clipboard) {

            Point startPoint = new Point(shape.getStartPointX() + 20, shape.getStartPointY() + 20);
            Point endPoint = new Point(shape.getStartPointX() + shape.getWidth() + 20,
                    shape.getStartPointY() + shape.getHeight() + 20);

            ShapeDrawing drawShapeContext =  new ShapeDrawing();
            IShape shapeStrategy = null;

            if (paintCanvas == null)
                paintCanvas = shape.getPaintCanvas();

            switch (shape.getClass().getSimpleName()) {
                case "Rectangle":
                    Rectangle rectShape = (Rectangle) shape;
                    shapeStrategy = new Rectangle(startPoint, endPoint, rectShape.getPaintCanvas(), rectShape.getShadeType(),
                            rectShape.getPrimaryColor(), rectShape.getSecondaryColor());
                    break;
                case "Ellipse":
                    Ellipse ellipseShape = (Ellipse) shape;
                    shapeStrategy = new Ellipse(startPoint, endPoint, ellipseShape.getPaintCanvas(), ellipseShape.getShadeType(),
                            ellipseShape.getPrimaryColor(), ellipseShape.getSecondaryColor());
                    break;
                case "Triangle":
                    Triangle triangleShape = (Triangle) shape;
                    int[] xValues = triangleShape.getXCoords();
                    int[] yValues = triangleShape.getYCoords();
                    startPoint = new Point(xValues[0] + 15, yValues[0] + 15);
                    endPoint = new Point(xValues[2] + 15, yValues[2] + 15);
                    shapeStrategy = new Triangle(startPoint, endPoint, triangleShape.getPaintCanvas(), triangleShape.getShadeType(),
                            triangleShape.getPrimaryColor(), triangleShape.getSecondaryColor());
                    break;
                case "GroupHandler":
                    GroupHandler shapeGroup = (GroupHandler) shape;
                    shapeStrategy = new GroupHandler(shapeGroup);
                    shapeStrategy.updateCoordinates(20, 20);
                    break;
                default:
                    break;
            }
            

            drawShapeContext.setDrawShapeStrategy(shapeStrategy);
            drawShapeContext.drawShape();

            if (drawShapeContext != null)
                shapetoPaste.add(shapeStrategy);
        }
    }

    @Override
    public void execute() {
        for (IShape shape : shapetoPaste) {
            if (shape != null) {
                ExistingShape.shapeList.add(shape);
                CommandInvoker.add(this);
            }
        }
        if (paintCanvas != null) {
            isPasteSelected = (ActiveShape.activeShape.size() > 0) ? true : false;

            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void undo() {
        // Move.setUndoSelected(false);
        MoveHandler.setUndoSelected(false);
        for (IShape shape : shapetoPaste)
        {
            if (shape != null)
                ExistingShape.shapeList.remove(shape);
        }
        if (paintCanvas != null) {
            isPasteSelected = (ActiveShape.activeShape.size() > 0) ? true : false;
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void redo() {
        // Move.setRedoSelected(false);
        MoveHandler.setRedoSelected(false);
        this.execute();
    }

    public static void setIsPasteSelected(boolean isPasteSelected) {
        PasteHandler.isPasteSelected = isPasteSelected;
    }

    public static boolean getIsPasteSelected() {
        return isPasteSelected;
    }

    public APaintCanvas getPaintCanvas() {
        return paintCanvas;
    }
}
