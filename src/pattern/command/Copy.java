package pattern.command;

import model.interfaces.IClipboard;
import pattern.command.interfaces.ICommand;
import model.interfaces.IActiveShape;

public class Copy implements ICommand, IClipboard, IActiveShape {

    @Override
    public void execute() {
        clipboard.clear();
        activeShape.forEach(shape -> clipboard.add(shape));
        System.out.println("The number of the shapes on the clipboard: " + clipboard.size());
    }
}
