package model.Proxy;

// ydeng24@depaul.edu

import model.DrawnShapesList;
import model.SelectedShapesList;
import model.Strategy.DrawEllipse;
import model.Strategy.DrawRectangle;
import model.Strategy.DrawTriangle;
import model.interfaces.ISelectedShapeOutline;
import model.interfaces.IShape;


// This class draws the selected shapes outline
public class SelectedShapeOutline implements ISelectedShapeOutline {

    RectangleOutline drawRectOutline = new RectangleOutline();
    EllipseOutline drawEllipseOutline = new EllipseOutline();
    TriangleOutline drawTriangleOutline = new TriangleOutline();

    @Override
    public void shapeOutline() {

        for (IShape temp1 : DrawnShapesList.shapeList) {
            for (IShape temp2 : SelectedShapesList.selectedShapes) {
                if (temp2.equals(temp1)) {
                    if (temp2 instanceof DrawRectangle) {
                        drawRectOutline.draw(temp2.getStartPointX(), temp2.getStartPointY(),
                                temp2.getWidth(), temp2.getHeight(), temp2.getPaintCanvas());
                    }
                    else if (temp2 instanceof DrawEllipse) {
                        drawEllipseOutline.draw(temp2.getStartPointX(), temp2.getStartPointY(),
                                temp2.getWidth(), temp2.getHeight(), temp2.getPaintCanvas());
                    }
                    else if (temp2 instanceof DrawTriangle) {
                        DrawTriangle tempTriangleValues = (DrawTriangle) temp2;
                        int[] xValues = tempTriangleValues.getXValues();
                        int[] yValues = tempTriangleValues.getYValues();
                        drawTriangleOutline.draw(xValues, yValues, temp2.getPaintCanvas());
                    }
                }
            }
        }
    }
}
