package pattern.command;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.Point;
import model.ActiveShape;
import pattern.strategy.*;
import model.interfaces.IClipboard;
import pattern.command.interfaces.ICommand;
import pattern.command.interfaces.IUndoable;
import pattern.factory.interfaces.IShape;
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
        Move.setUndoSelected(false);
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
