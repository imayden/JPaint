package view.gui;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import javax.swing.JPanel;
import java.awt.Rectangle;
import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

public class DrawingPanel extends JPanel {
    private ArrayList<Rectangle> rectangles;

    public DrawingPanel(ArrayList<Rectangle> rectangles) {
        this.rectangles = rectangles;
        setBackground(Color.WHITE); 
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLACK);
        for (Rectangle rect : rectangles) {
            g.fillRect((int)rect.getX(), (int)rect.getY(), (int)rect.getWidth(), (int)rect.getHeight());
        }
    }
}
