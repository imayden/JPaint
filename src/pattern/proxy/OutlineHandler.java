package  pattern.proxy;

// ydeng24@depaul.edu

import model.ExistingShape;
import model.ActiveShape;
import pattern.strategy.*;
import model.interfaces.IShape;
import pattern.proxy.interfaces.IOutlineHandler;


// This class draws the selected shapes outline
public class OutlineHandler implements IOutlineHandler {

    RectangleOutline drawRectOutline = new RectangleOutline();
    EllipseOutline drawEllipseOutline = new EllipseOutline();
    TriangleOutline drawTriangleOutline = new TriangleOutline();

    @Override
    public void shapeOutline() {

        for (IShape temp1 : ExistingShape.shapeList) {
            for (IShape temp2 : ActiveShape.selectedShapes) {
                if (temp2.equals(temp1)) {
                    if (temp2 instanceof Rectangle) {
                        drawRectOutline.draw(temp2.getStartPointX(), temp2.getStartPointY(),
                                temp2.getWidth(), temp2.getHeight(), temp2.getPaintCanvas());
                    }
                    else if (temp2 instanceof Ellipse) {
                        drawEllipseOutline.draw(temp2.getStartPointX(), temp2.getStartPointY(),
                                temp2.getWidth(), temp2.getHeight(), temp2.getPaintCanvas());
                    }
                    else if (temp2 instanceof Triangle) {
                        Triangle tempTriangleValues = (Triangle) temp2;
                        int[] xValues = tempTriangleValues.getXValues();
                        int[] yValues = tempTriangleValues.getYValues();
                        drawTriangleOutline.draw(xValues, yValues, temp2.getPaintCanvas());
                    }
                }
            }
        }
    }
}
