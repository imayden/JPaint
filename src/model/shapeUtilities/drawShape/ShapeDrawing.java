package model.shapeUtilities.drawShape;

// Design Pattern Used: Strategy

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.interfaces.IShape;

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
