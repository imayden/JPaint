package controller;

// ydeng24@depaul.edu

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
        uiModule.addEvent(EventName.CHOOSE_START_POINT_ENDPOINT_MODE, () -> applicationState.setActiveStartAndEndPointMode());

        // Checkin 1: Undo, Redo
        uiModule.addEvent(EventName.UNDO, () -> applicationState.undoCommand());
        uiModule.addEvent(EventName.REDO, () -> applicationState.redoCommand());

        // Checkin 2: Copy, Paste, Delte
        uiModule.addEvent(EventName.COPY, () -> applicationState.copyCommand());
        uiModule.addEvent(EventName.PASTE, () -> applicationState.pasteCommand());
        uiModule.addEvent(EventName.DELETE, () -> applicationState.deleteCommand());

        // Checkin 4: Group, Ungroup
        // uiModule.addEvent(EventName.GROUP, () -> applicationState.groupCommand());
        // uiModule.addEvent(EventName.UNGROUP, () -> applicationState.ungroupCommand());
    }
}
