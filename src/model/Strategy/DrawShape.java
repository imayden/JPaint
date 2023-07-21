package model.Strategy;

// ydeng24@depaul.edu

import model.interfaces.IShape;

public class DrawShape  {

    private IShape drawShapeStrategy;

    public DrawShape() { }

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
