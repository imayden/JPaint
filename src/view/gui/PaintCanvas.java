package view.gui;

import java.awt.*;
public class PaintCanvas extends APaintCanvas {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
}
