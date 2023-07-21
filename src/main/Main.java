package main;

// ydeng24@depaul.edu

import controller.IJPaintController;
import controller.JPaintController;
import model.ShapeColor;
import model.Singleton.MouseListener;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IPaintCanvas;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        IPaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        // Set up controller
        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        // Initialize colors map
        ShapeColor.getMap();

        // Set up mouse listener
        MouseListener mouseListener = MouseListener.getInstance();
        paintCanvas.addMouseListener(mouseListener);
        mouseListener.setSettings(paintCanvas, appState);
    }
}
