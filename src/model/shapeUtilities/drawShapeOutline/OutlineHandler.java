package  model.shapeUtilities.drawShapeOutline;

// Design Pattern Used: Strategy, Command

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.command.CommandHandler.GroupHandler;
import model.interfaces.IOutlineHandler;
import model.interfaces.IShape;
import model.shapeUtilities.ActiveShape;
import model.shapeUtilities.ExistingShape;
import model.shapeUtilities.drawShape.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

public class OutlineHandler implements IOutlineHandler {

    private final Map<Class<? extends IShape>, Consumer<IShape>> shapeDrawers = new HashMap<>();

    public OutlineHandler() {
        RectangleOutline drawRectangleOutline = new RectangleOutline();
        EllipseOutline drawEllipseOutline = new EllipseOutline();
        TriangleOutline drawTriangleOutline = new TriangleOutline();

        shapeDrawers.put(Rectangle.class, shape -> drawRectangleOutline.draw(shape.getStartPointX(), shape.getStartPointY(), shape.getWidth(), shape.getHeight(), shape.getPaintCanvas()));
        shapeDrawers.put(Ellipse.class, shape -> drawEllipseOutline.draw(shape.getStartPointX(), shape.getStartPointY(), shape.getWidth(), shape.getHeight(), shape.getPaintCanvas()));
        shapeDrawers.put(Triangle.class, shape -> {
            Triangle triangle = (Triangle) shape;
            drawTriangleOutline.draw(triangle.getXCoords(), triangle.getYCoords(), shape.getPaintCanvas());
        });
        shapeDrawers.put(GroupHandler.class, shape -> drawRectangleOutline.draw(shape.getStartPointX(), shape.getStartPointY(), shape.getWidth(), shape.getHeight(), shape.getPaintCanvas()));
    }

    @Override
    public void outline() {
        for (IShape shape : ExistingShape.shapeList) {
            for (IShape outlineShape : ActiveShape.activeShape) {
                if (outlineShape.equals(shape)) {
                    if (shapeDrawers.containsKey(outlineShape.getClass())) {
                        shapeDrawers.get(outlineShape.getClass()).accept(outlineShape);
                    }
                }
            }
        }
    }
}
