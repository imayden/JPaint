package pattern.proxy;

// ydeng24@depaul.edu

import view.interfaces.IPaintCanvas;
import java.awt.*;

public class TriangleOutline {

    public void draw(int xValues[], int yValues[], IPaintCanvas paintCanvas) {

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1,
                new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        int[] tempXValues = {0, 0, 0};
        int[] tempYValues = {0, 0, 0};

        if (xValues[0] < xValues[2]) {
            tempXValues[0] = xValues[0] - 5;
            tempXValues[1] = xValues[1] - 5;
            tempXValues[2] = xValues[2] + 10;
        } else {
            tempXValues[2] = xValues[2] - 10;
            tempXValues[1] = xValues[1] + 5;
            tempXValues[0] = xValues[0] + 5;
        }
        if (yValues[0] < yValues[2]) {
            tempYValues[0] = yValues[0] - 10;
            tempYValues[1] = yValues[1] + 5;
            tempYValues[2] = yValues[2] + 5;
        } else {
            tempYValues[2] = yValues[2] - 5;
            tempYValues[1] = yValues[1] - 5;
            tempYValues[0] = yValues[0] + 10;
        }
        graphics2d.drawPolygon(tempXValues, tempYValues, 3);
   }
}
