package pattern.proxy;

import view.interfaces.IPaintCanvas;

import java.awt.*;

public class TriangleOutline {

    private static final int OUTLINE_THICKNESS = 3;
    private static final float[] DASH_PATTERN = {9};
    private static final int OFFSET = 5;
    private static final int OFFSET_DIAGONAL = 10;
    private static final int COORDINATES_COUNT = 3;

    public void draw(int[] xCoords, int[] yCoords, IPaintCanvas paintCanvas) {

        Graphics2D graphics2d = paintCanvas.getGraphics2D();
        Stroke stroke = new BasicStroke(OUTLINE_THICKNESS, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 1,
                DASH_PATTERN, 0);
        graphics2d.setStroke(stroke);
        graphics2d.setColor(Color.BLACK);
        
        int[] outlineXCoords = new int[COORDINATES_COUNT];
        int[] outlineYCoords = new int[COORDINATES_COUNT];

        if (xCoords[0] < xCoords[2]) {
            outlineXCoords[0] = xCoords[0] - OFFSET;
            outlineXCoords[1] = xCoords[1] - OFFSET;
            outlineXCoords[2] = xCoords[2] + OFFSET_DIAGONAL;
        } else {
            outlineXCoords[2] = xCoords[2] - OFFSET_DIAGONAL;
            outlineXCoords[1] = xCoords[1] + OFFSET;
            outlineXCoords[0] = xCoords[0] + OFFSET;
        }
        if (yCoords[0] < yCoords[2]) {
            outlineYCoords[0] = yCoords[0] - OFFSET_DIAGONAL;
            outlineYCoords[1] = yCoords[1] + OFFSET;
            outlineYCoords[2] = yCoords[2] + OFFSET;
        } else {
            outlineYCoords[2] = yCoords[2] - OFFSET;
            outlineYCoords[1] = yCoords[1] - OFFSET;
            outlineYCoords[0] = yCoords[0] + OFFSET_DIAGONAL;
        }
        graphics2d.drawPolygon(outlineXCoords, outlineYCoords, COORDINATES_COUNT);
    }
}
