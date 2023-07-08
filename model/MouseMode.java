package model;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import command.Action;
import command.DrawAction;
import view.gui.DrawingPanel;

import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Stack;

public class MouseMode {
    private int x = -10, y = -10, x2 = -10, y2 = -10;
    private ArrayList<Rectangle> rectangles;
    private Stack<Action> undoStack;

    public MouseMode(ArrayList<Rectangle> rectangles, Stack<Action> undoStack, Stack<Action> redoStack) {
        this.rectangles = rectangles;
        this.undoStack = undoStack;
    }

    public void addListenersToPanel(DrawingPanel drawingPanel) {
        drawingPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                x = e.getX();
                y = e.getY();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
                Rectangle rectangle = new Rectangle(Math.min(x, x2), Math.min(y, y2), Math.abs(x - x2), Math.abs(y - y2));
                rectangles.add(rectangle);
                undoStack.push(new DrawAction(rectangles, rectangle));
                drawingPanel.repaint();
            }
        });

        drawingPanel.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                x2 = e.getX();
                y2 = e.getY();
            }
        });
    }
}
