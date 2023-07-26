package  pattern.proxy;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.ActiveShape;
import pattern.strategy.*;
import model.interfaces.IShape;
import pattern.proxy.interfaces.IOutlineHandler;

public class OutlineHandler implements IOutlineHandler {

    RectangleOutline drawRectangleOutline = new RectangleOutline();
    EllipseOutline drawEllipseOutline = new EllipseOutline();
    TriangleOutline drawTriangleOutline = new TriangleOutline();

    @Override
    public void outline() {

        for (IShape shape : ExistingShape.shapeList) {
            for (IShape outlineShape : ActiveShape.activeShape) {
                if (outlineShape.equals(shape)) {
                    if (outlineShape instanceof Rectangle) {
                        drawRectangleOutline.draw(outlineShape.getStartPointX(), outlineShape.getStartPointY(),
                                outlineShape.getWidth(), outlineShape.getHeight(), outlineShape.getPaintCanvas());
                    }
                    else if (outlineShape instanceof Ellipse) {
                        drawEllipseOutline.draw(outlineShape.getStartPointX(), outlineShape.getStartPointY(),
                                outlineShape.getWidth(), outlineShape.getHeight(), outlineShape.getPaintCanvas());
                    }
                    else if (outlineShape instanceof Triangle) {
                        Triangle triangle = (Triangle) outlineShape;
                        int[] xValues = triangle.getXCoords();
                        int[] yValues = triangle.getYCoords();
                        drawTriangleOutline.draw(xValues, yValues, outlineShape.getPaintCanvas());
                    }
                }
            }
        }
    }
}
