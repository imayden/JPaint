package pattern.singleton;

// ydeng24@depaul.edu

import model.persistence.ApplicationState;
import pattern.command.ActiveMouseMode;
import view.interfaces.IPaintCanvas;
import model.Point;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseListener extends MouseAdapter {

    private Point startPoint;
    private Point endPoint;
    private static IPaintCanvas paintCanvas;
    private static ApplicationState appState;
    private static MouseListener myMouseListenerObj;

    public static IPaintCanvas getPaintCanvas() {
        return MouseListener.paintCanvas;
    };

    private MouseListener() { }

    public static MouseListener getInstance() {
        if(myMouseListenerObj == null) {
            myMouseListenerObj = new MouseListener();
        }
        return myMouseListenerObj;
    }

    public void setSettings(IPaintCanvas paintCanvas, ApplicationState appState) {
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

