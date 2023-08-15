package view.gui;

// ydeng24@depaul.edu

import model.*;
import model.command.PasteHandler;
import model.interfaces.IShape;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShapeOutline.OutlineHandler;
import view.interfaces.IPaintCanvas;
import java.awt.*;

public abstract class UpdateCanvas {

    private static OutlineHandler selectedShapeOutline = new OutlineHandler();

    public static void update(IPaintCanvas paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        // Redraw updated shapes
        for (IShape shape : ExistingShape.shapeList) {
            shape.drawShape();

            // if (Paste.isIsPasteSelected() && ActiveShape.activeShape.contains(shape)) {
            if (PasteHandler.isIsPasteSelected() && ActiveShape.activeShape.contains(shape)) {
                selectedShapeOutline.outline();
                // Paste.setIsPasteSelected(false);
                PasteHandler.setIsPasteSelected(false);
            }
        }
    }
}

