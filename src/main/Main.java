package main;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import controller.JPaintController;
import controller.interfaces.IJPaintController;
import model.mouseUtilities.MouseListener;
import model.persistence.ApplicationState;
import view.gui.APaintCanvas;
import view.gui.Gui;
import view.gui.GuiWindow;
import view.gui.PaintCanvas;
import view.interfaces.IGuiWindow;
import view.interfaces.IUiModule;

public class Main {
    public static void main(String[] args) {
        ApplicationSetup setup = new ApplicationSetup();
        setup.initialize();

        MouseListener mouseListener = MouseListener.getInstance();
        setup.getPaintCanvas().addMouseListener(mouseListener);
        mouseListener.setSettings(setup.getPaintCanvas(), setup.getAppState());
    }
}

class ApplicationSetup {
    private APaintCanvas paintCanvas;
    private IGuiWindow guiWindow;
    private IUiModule uiModule;
    private ApplicationState appState;
    private IJPaintController controller;

    public void initialize() {
        paintCanvas = new PaintCanvas();
        guiWindow = new GuiWindow(paintCanvas);
        uiModule = new Gui(guiWindow);
        appState = new ApplicationState(uiModule);
        controller = new JPaintController(uiModule, appState);
        controller.setup();
    }

    public APaintCanvas getPaintCanvas() {
        return paintCanvas;
    }

    public ApplicationState getAppState() {
        return appState;
    }
}
