package view.gui;

// ydeng24@depaul.edu

import model.*;
import model.interfaces.IShape;
import pattern.command.Paste;
import pattern.proxy.OutlineHandler;
import view.interfaces.IPaintCanvas;
import java.awt.*;

// Update canvas
public abstract class UpdateCanvas {

    private static OutlineHandler selectedShapeOutline = new OutlineHandler();

    public static void update(IPaintCanvas paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        // Redraw updated shapes
        for (IShape shape : ExistingShape.shapeList) {
            shape.drawShape();

            if (Paste.isIsPasteSelected() && ActiveShape.selectedShapes.contains(shape)) {
                selectedShapeOutline.shapeOutline();
                Paste.setIsPasteSelected(false);
            }
        }
    }
}

