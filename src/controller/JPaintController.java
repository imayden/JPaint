package controller;

// SE450 Final Project
// ydeng24@depaul.edu
// © 2023 Ayden Deng

import controller.interfaces.IJPaintController;
import model.interfaces.IApplicationState;
import view.EventName;
import view.interfaces.IUiModule;

public class JPaintController implements IJPaintController {
    private final IUiModule uiModule;
    private final IApplicationState applicationState;

    public JPaintController(IUiModule uiModule, IApplicationState applicationState) {
        this.uiModule = uiModule;
        this.applicationState = applicationState;
    }

    @Override
    public void setup() {
        setupEvents();
    }

    private void setupEvents() {
        uiModule.addEvent(EventName.CHOOSE_SHAPE, () -> applicationState.setActiveShape());
        uiModule.addEvent(EventName.CHOOSE_PRIMARY_COLOR, () -> applicationState.setActivePrimaryColor());
        uiModule.addEvent(EventName.CHOOSE_SECONDARY_COLOR, () -> applicationState.setActiveSecondaryColor());
        uiModule.addEvent(EventName.CHOOSE_SHADING_TYPE, () -> applicationState.setActiveShadingType());
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveMouseMode());

        // Checkin 1: Undo, Redo
        uiModule.addEvent(EventName.UNDO, () -> applicationState.undo());
        uiModule.addEvent(EventName.REDO, () -> applicationState.redo());

        // Checkin 3: Copy, Paste, Delete
        uiModule.addEvent(EventName.COPY, () -> applicationState.copy());
        uiModule.addEvent(EventName.PASTE, () -> applicationState.paste());
        uiModule.addEvent(EventName.DELETE, () -> applicationState.delete());

        // Checkin 4: Group, Ungroup
        uiModule.addEvent(EventName.GROUP, () -> applicationState.group());
        uiModule.addEvent(EventName.UNGROUP, () -> applicationState.ungroup());
    }
}
