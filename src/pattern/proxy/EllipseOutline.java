package pattern.proxy;

// ydeng24@depaul.edu
// Final Project

import java.awt.*;
import model.Point;
import view.interfaces.IPaintCanvas;

public class EllipseOutline {
    private static final int OUTLINE_THICKNESS = 3;
    private static final int OUTLINE_OFFSET = 5;
    private static final float[] DASH_PATTERN = {9};

    public void draw(int startPointX, int startPointY, int width, int height, IPaintCanvas paintCanvas) {
        Point[] points = calculatePoints(startPointX, startPointY, width, height);
        drawOutline(points, paintCanvas);
    }

    private Point[] calculatePoints(int startPointX, int startPointY, int width, int height) {
        Point startPoint = new Point(startPointX - OUTLINE_OFFSET, startPointY - OUTLINE_OFFSET);
        Point endPoint = new Point (startPointX + width + OUTLINE_OFFSET, startPointY + height + OUTLINE_OFFSET);
        return new Point[] {startPoint, endPoint};
    }

    private void drawOutline(Point[] points, IPaintCanvas paintCanvas) {
        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(OUTLINE_THICKNESS, BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 1, DASH_PATTERN, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        graphics2d.drawOval(points[0].getX(), points[0].getY(), Math.abs(points[0].getX() - points[1].getX()),
                Math.abs(points[0].getY() - points[1].getY()));
    }
}

