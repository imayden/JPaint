package model.mouseUtilities;

// Design Pattern Used: Singleton

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import model.persistence.ApplicationState;
import view.gui.APaintCanvas;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    private static APaintCanvas paintCanvas;
    private static ApplicationState appState;
    private static MouseListener myMouseListenerObj;

    public static APaintCanvas getPaintCanvas() {
        return MouseListener.paintCanvas;
    };

    private MouseListener() { }

    public static MouseListener getInstance() {
        if(myMouseListenerObj == null) {
            myMouseListenerObj = new MouseListener();
        }
        return myMouseListenerObj;
    }

    public void setSettings(APaintCanvas paintCanvas, ApplicationState appState) {
        this.paintCanvas = paintCanvas;
        this.appState = appState;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        startPoint = new Point(e.getX(),e.getY());
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        endPoint = new Point(e.getX(),e.getY());
        ActiveMouseMode.clickedMode(startPoint, endPoint, paintCanvas, appState);
    }

}

