package pattern.strategy;

import pattern.factory.interfaces.IShape;

public class ShapeDrawing  {

    private IShape drawShapeStrategy;

    public ShapeDrawing() { }

    public void setDrawShapeStrategy(IShape drawShapeStrategy) {
        this.drawShapeStrategy = drawShapeStrategy;
    }

    public void drawShape() {
        drawShapeStrategy.drawShape();
    }

    public IShape getDrawShapeStrategy() {
        return drawShapeStrategy;
    }

}