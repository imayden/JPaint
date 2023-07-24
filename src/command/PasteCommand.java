package command;

// ydeng24@depaul.edu

import model.DrawnShapesList;
import model.Point;
import model.SelectedShapesList;
import model.Strategy.DrawEllipse;
import model.Strategy.DrawRectangle;
import model.Strategy.DrawShape;
import model.Strategy.DrawTriangle;
import model.interfaces.ICommand;
import model.interfaces.IClipboardCommand;
import model.interfaces.IShape;
import model.interfaces.IUndoable;
import view.gui.UpdateCanvas;
import view.interfaces.IPaintCanvas;
import java.util.LinkedList;

// Paste shape from clipboard.

public class PasteCommand implements IClipboardCommand, ICommand, IUndoable {

    private LinkedList<IShape> shapetoPaste;
    private IPaintCanvas paintCanvas = null;
    private static boolean isPasteSelected = false;

    public PasteCommand() {
        shapetoPaste = new LinkedList<>();

        for (IShape shape : clipboardShapes) {


            // Calculate offset
            Point tempStartPoint = new Point(shape.getStartPointX() + 20, shape.getStartPointY() + 20);
            Point tempEndPoint = new Point(shape.getStartPointX() + shape.getWidth() + 20,
                    shape.getStartPointY() + shape.getHeight() + 20);

            DrawShape drawShapeContext =  new DrawShape();
            IShape shapeStrategy = null;

            if (paintCanvas == null)
                paintCanvas = shape.getPaintCanvas();

            // Create and draw a new shape on the canvas
            if (shape instanceof DrawRectangle) {
                DrawRectangle rectShape = (DrawRectangle) shape;
                shapeStrategy = new DrawRectangle(tempStartPoint, tempEndPoint, rectShape.getPaintCanvas(), rectShape.getShadeType(),
                        rectShape.getPrimaryColor(), rectShape.getSecondaryColor());
            } else if (shape instanceof DrawEllipse) {
                DrawEllipse ellipseShape = (DrawEllipse) shape;
                shapeStrategy = new DrawEllipse(tempStartPoint, tempEndPoint, ellipseShape.getPaintCanvas(), ellipseShape.getShadeType(),
                        ellipseShape.getPrimaryColor(), ellipseShape.getSecondaryColor());
            } else if (shape instanceof DrawTriangle) {
                DrawTriangle triangleShape = (DrawTriangle) shape;
                int[] xValues = triangleShape.getXValues();
                int[] yValues = triangleShape.getYValues();
                tempStartPoint = new Point(xValues[0] + 15, yValues[0] + 15);
                tempEndPoint = new Point(xValues[2] + 15, yValues[2] + 15);
                shapeStrategy = new DrawTriangle(tempStartPoint, tempEndPoint, triangleShape.getPaintCanvas(), triangleShape.getShadeType(),
                        triangleShape.getPrimaryColor(), triangleShape.getSecondaryColor());
            }

            drawShapeContext.setDrawShapeStrategy(shapeStrategy);
            drawShapeContext.drawShape();

            if (drawShapeContext != null)
                shapetoPaste.add(shapeStrategy);
        }
    }

    @Override
    public void run() {
        for (IShape temp : shapetoPaste) {
            if (temp != null) {
                DrawnShapesList.shapeList.add(temp);
                CommandInvoker.add(this);
            }
        }
        if (paintCanvas != null) {
            isPasteSelected = (SelectedShapesList.selectedShapes.size() > 0) ? true : false;

            // Update canvas
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void undo() {
        MoveCommand.setUndoSelected(false);
        for (IShape temp : shapetoPaste)
        {
            if (temp != null)
                DrawnShapesList.shapeList.remove(temp);
        }
        if (paintCanvas != null) {
            isPasteSelected = (SelectedShapesList.selectedShapes.size() > 0) ? true : false;
            UpdateCanvas.update(paintCanvas);
        }
    }

    @Override
    public void redo() {
        MoveCommand.setRedoSelected(false);
        this.run();
    }

    public static void setIsPasteSelected(boolean isPasteSelected) {
        PasteCommand.isPasteSelected = isPasteSelected;
    }

    public static boolean isIsPasteSelected() {
        return isPasteSelected;
    }
}
