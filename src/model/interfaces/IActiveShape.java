package model.interfaces;

// ydeng24@depaul.edu

import java.util.LinkedList;

import pattern.factory.interfaces.IShape;

public interface IActiveShape {
    LinkedList<IShape> activeShape = new LinkedList<>();
}
