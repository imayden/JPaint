package view.gui;

// ydeng24@depaul.edu

import view.interfaces.IPaintCanvas;
import java.awt.*;
public class PaintCanvas extends IPaintCanvas {

    public Graphics2D getGraphics2D() {
        return (Graphics2D)getGraphics();
    }
}
