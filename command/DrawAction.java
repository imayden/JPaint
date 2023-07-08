package command;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import java.awt.Rectangle;
import java.util.ArrayList;

public class DrawAction implements Action {
    private ArrayList<Rectangle> rectangles;
    private final Rectangle rectangle;

    public DrawAction(ArrayList<Rectangle> rectangles, Rectangle rectangle) {
        this.rectangles = rectangles;
        this.rectangle = rectangle;
    }

    @Override
    public void undo() {
        rectangles.remove(rectangle);
    }

    @Override
    public void redo() {
        rectangles.add(rectangle);
    }
}
