package pattern.command;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.IActiveShape;
import pattern.command.interfaces.ICommand;
import pattern.composite.ShapeGroup;
import pattern.factory.interfaces.IShape;
import pattern.singleton.MouseListener;

// ydeng24@depaul.edu

public class Group implements ICommand {
    // Implementation at Checkin 4
    

    public void execute() {
        ShapeGroup shapeGroup = new ShapeGroup(MouseListener.getPaintCanvas());
        shapeGroup.group();
    }
}
