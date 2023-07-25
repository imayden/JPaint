package pattern.command;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.Point;
import model.ActiveShape;
import pattern.strategy.*;
import model.interfaces.IClipboard;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import pattern.command.interfaces.ICommand;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

// Paste shape from clipboard.

public class Paste implements IClipboard, ICommand, IUndoable {

    private LinkedList<IShape> shapetoPaste;
    private IPaintCanvas paintCanvas = null;
    private static boolean isPasteSelected = false;

    public Paste() {
        shapetoPaste = new LinkedList<>();

        for (IShape shape : clipboardShapes) {


            // Calculate offset
            Point tempStartPoint = new Point(shape.getStartPointX() + 20, shape.getStartPointY() + 20);
            Point tempEndPoint = new Point(shape.getStartPointX() + shape.getWidth() + 20,
                    shape.getStartPointY() + shape.getHeight() + 20);

            ShapeDrawing drawShapeContext =  new ShapeDrawing();
            IShape shapeStrategy = null;

            if (paintCanvas == null)
                paintCanvas = shape.getPaintCanvas();

            // Create and draw a new shape on the canvas
            if (shape instanceof Rectangle) {
                Rectangle rectShape = (Rectangle) shape;
                shapeStrategy = new Rectangle(tempStartPoint, tempEndPoint, rectShape.getPaintCanvas(), rectShape.getShadeType(),
                        rectShape.getPrimaryColor(), rectShape.getSecondaryColor());
            } else if (shape instanceof Ellipse) {
                Ellipse ellipseShape = (Ellipse) shape;
                shapeStrategy = new Ellipse(tempStartPoint, tempEndPoint, ellipseShape.getPaintCanvas(), ellipseShape.getShadeType(),
                        ellipseShape.getPrimaryColor(), ellipseShape.getSecondaryColor());
            } else if (shape instanceof Triangle) {
                Triangle triangleShape = (Triangle) shape;
                int[] xValues = triangleShape.getXValues();
                int[] yValues = triangleShape.getYValues();
                tempStartPoint = new Point(xValues[0] + 15, yValues[0] + 15);
                tempEndPoint = new Point(xValues[2] + 15, yValues[2] + 15);
                shapeStrategy = new Triangle(tempStartPoint, tempEndPoint, triangleShape.getPaintCanvas(), triangleShape.getShadeType(),
                        triangleShape.getPrimaryColor(), triangleShape.getSecondaryColor());
            }

            drawShapeContext.setDrawShapeStrategy(shapeStrategy);
            drawShapeContext.drawShape();

            if (drawShapeContext != null)
                shapetoPaste.add(shapeStrategy);
        }
    }

    @Override
    public void execute() {
        for (IShape temp : shapetoPaste) {
            if (temp != null) {
                ExistingShape.shapeList.add(temp);
                CommandInvoker.add(this);
            }
        }
        if (paintCanvas != null) {
            isPasteSelected = (ActiveShape.selectedShapes.size() > 0) ? true : false;

            // Update canvas
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void undo() {
        Move.setUndoSelected(false);
        for (IShape temp : shapetoPaste)
        {
            if (temp != null)
                ExistingShape.shapeList.remove(temp);
        }
        if (paintCanvas != null) {
            isPasteSelected = (ActiveShape.selectedShapes.size() > 0) ? true : false;
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void redo() {
        Move.setRedoSelected(false);
        this.execute();
    }

    public static void setIsPasteSelected(boolean isPasteSelected) {
        Paste.isPasteSelected = isPasteSelected;
    }

    public static boolean isIsPasteSelected() {
        return isPasteSelected;
    }
}
