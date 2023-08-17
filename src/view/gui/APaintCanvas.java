package view.gui;

// ydeng24@depaul.edu

import javax.swing.*;
import java.awt.*;

public abstract class APaintCanvas extends JComponent {
    public abstract Graphics2D getGraphics2D();
}
