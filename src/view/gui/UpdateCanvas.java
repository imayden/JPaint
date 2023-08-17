package view.gui;

// Design Pattern Used: Template

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandHandler.PasteHandler;
import model.interfaces.IShape;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShapeOutline.OutlineHandler;

import java.awt.*;

public abstract class UpdateCanvas {

    private static OutlineHandler selectedShapeOutline = new OutlineHandler();

    public static void update(APaintCanvas paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        graphics2d.setColor(Color.white);
        graphics2d.fillRect(0, 0, paintCanvas.getWidth(), paintCanvas.getHeight());

        for (IShape shape : ExistingShape.shapeList) {
            shape.drawShape();

            if (PasteHandler.getIsPasteSelected() && ActiveShape.activeShape.contains(shape)) {
                selectedShapeOutline.outline();
                PasteHandler.setIsPasteSelected(false);
            }
        }
    }
}

