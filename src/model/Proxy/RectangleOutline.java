package model.Proxy;

// ydeng24@depaul.edu

import java.awt.*;
import model.Point;
import view.interfaces.IPaintCanvas;

public class RectangleOutline {

    public void draw(int startPointX, int startPointY, int width, int height, IPaintCanvas paintCanvas) {

        Point startPoint = new Point(startPointX - 5,startPointY - 5);
        Point endPoint = new Point (startPointX + width + 5, startPointY + height + 5);

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(3, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 1, new float[]{9}, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawRect(startPoint.getX(), startPoint.getY(), Math.abs(startPoint.getX() - endPoint.getX()),
                Math.abs(startPoint.getY() - endPoint.getY()));
    }
}
