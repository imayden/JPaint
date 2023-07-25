package main;

// ydeng24@depaul.edu

import controller.JPaintController;
import controller.interfaces.IJPaintController;
import model.ShapeColor;
import model.persistence.ApplicationState;
import pattern.singleton.MouseListener;
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
