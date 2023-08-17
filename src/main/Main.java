package main;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import controller.JPaintController;
import controller.interfaces.IJPaintController;
import model.colorUtilities.ShapeColorFactory;
import model.mouseUtilities.MouseListener;
import model.persistence.ApplicationState;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.APaintCanvas;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        APaintCanvas paintCanvas = new PaintCanvas();
        IGuiWindow guiWindow = new GuiWindow(paintCanvas);
        IUiModule uiModule = new Gui(guiWindow);
        ApplicationState appState = new ApplicationState(uiModule);

        IJPaintController controller = new JPaintController(uiModule, appState);
        controller.setup();

        ShapeColorFactory.getMap();

        MouseListener mouseListener = MouseListener.getInstance();
        paintCanvas.addMouseListener(mouseListener);
        mouseListener.setSettings(paintCanvas, appState);
    }
}