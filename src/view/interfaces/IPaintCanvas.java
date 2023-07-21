package view.interfaces;

// ydeng24@depaul.edu

import javax.swing.*;
import java.awt.*;

public abstract class IPaintCanvas extends JComponent {
    public abstract Graphics2D getGraphics2D();
}
