package model.interfaces;

// ydeng24@depaul.edu

import java.util.LinkedList;

import pattern.factory.interfaces.IShape;

public interface IExistingShape {
    public LinkedList<IShape> shapeList = new LinkedList<>();
}
