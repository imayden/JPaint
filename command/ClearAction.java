package command;

/* 
 * Campus Connect E-mail: ydeng24@depaul.edu
 * Github: https://github.com/imayden
*/

import java.awt.Rectangle;
import java.util.ArrayList;

public class ClearAction implements Action {
    private ArrayList<Rectangle> rectangles;
    private final ArrayList<Rectangle> clearedRectangles = new ArrayList<>();

    public ClearAction(ArrayList<Rectangle> rectangles) {
        this.rectangles = rectangles;
        this.clearedRectangles.addAll(rectangles);
    }

    @Override
    public void undo() {
        rectangles.addAll(clearedRectangles);
    }

    @Override
    public void redo() {
        rectangles.clear();
    }
}
