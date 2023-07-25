package model;

// ydeng24@depaul.edu

// Get coordinates of x and y for cursor
public class Point {

    private int x = 0;
    private int y = 0;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Point() {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

}
