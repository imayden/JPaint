package view.gui;

// ydeng24@depaul.edu

import model.*;
import model.Proxy.SelectedShapeOutline;
import model.interfaces.IShape;
import view.interfaces.IPaintCanvas;
import java.awt.*;

import command.PasteCommand;

// Update canvas
public abstract class UpdateCanvas {

    private static SelectedShapeOutline selectedShapeOutline = new SelectedShapeOutline();

    public static void update(IPaintCanvas paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        // Redraw updated shapes
        for (IShape shape : DrawnShapesList.shapeList) {
            shape.drawShape();

            if (PasteCommand.isIsPasteSelected() && SelectedShapesList.selectedShapes.contains(shape)) {
                selectedShapeOutline.shapeOutline();
                PasteCommand.setIsPasteSelected(false);
            }
        }
    }
}

