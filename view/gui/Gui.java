package view.gui;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import command.Action;
import model.MouseMode;
import controller.DrawingController;


import java.awt.Rectangle;
import java.awt.BorderLayout;
import javax.swing.JFrame;
import java.util.ArrayList;
import java.util.Stack;

public class Gui extends JFrame {

    private ArrayList<Rectangle> rectangles = new ArrayList<>();
    private Stack<Action> undoStack = new Stack<>();
    private Stack<Action> redoStack = new Stack<>();


    public Gui() {
        setTitle("JPaint by YDENG24@depaul.edu");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        DrawingPanel drawingPanel = new DrawingPanel(rectangles);
        drawingPanel.setLayout(new BorderLayout());

        ButtonPanel buttonPanel = new ButtonPanel();
        drawingPanel.add(buttonPanel, BorderLayout.SOUTH);
    
        DrawingController drawingController = new DrawingController(rectangles, undoStack, redoStack);
        
        buttonPanel.getUndoButton().addActionListener(e -> {
            drawingController.undo();
            repaint();
        });
        buttonPanel.getRedoButton().addActionListener(e -> {
            drawingController.redo();
            repaint();
        });
        buttonPanel.getClearButton().addActionListener(e -> {
            drawingController.clear();
            repaint();
        });

        MouseMode mouseMode = new MouseMode(rectangles, undoStack, redoStack);
        mouseMode.addListenersToPanel(drawingPanel);

        this.add(drawingPanel);
        setVisible(true);
    }

}