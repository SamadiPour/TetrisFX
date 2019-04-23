package Components.Bricks;

import Components.Specification.Coordinate;
import Components.Specification.Dimension;
import Enums.Direction;
import Enums.Orientation;
import Enums.RotateDirection;
import Module.Setting;
import javafx.scene.paint.Color;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;

abstract public class FatherBrick implements Cloneable {
    private final HashMap<Orientation, int[][]> brickMatrixMap;
    private final HashMap<Orientation, Dimension> dimensionMap;
    private LinkedList<Orientation> availableOrientations;
    private final String name;
    private final Color color;
    private boolean isFreeze;
    private Orientation currentOrientation;
    private Coordinate currentCoordinate;

    public FatherBrick(String name, Color color, Orientation currentOrientation, HashMap<Orientation, int[][]> brickMatrixMap, HashMap<Orientation, Dimension> dimensionMap, LinkedList<Orientation> availableOrientations, boolean isFreeze, Coordinate currentCoordinate) {
        this.brickMatrixMap = brickMatrixMap;
        this.dimensionMap = dimensionMap;
        this.availableOrientations = availableOrientations;
        this.name = name;
        this.color = color;
        this.currentOrientation = currentOrientation;
        this.isFreeze = isFreeze;
        this.currentCoordinate = currentCoordinate;
    }

    public Dimension getCurrentDimension() {
        return dimensionMap.get(currentOrientation);
    }

    public Color getColor() {
        return color;
    }

    public int getCurrentX() {
        return currentCoordinate.getCurrentX();
    }

    public int getCurrentY() {
        return currentCoordinate.getCurrentY();
    }

    public Orientation getCurrentOrientation() {
        return currentOrientation;
    }

    public boolean isFreeze() {
        return isFreeze;
    }

    public int[][] getBrickMatrix() {
        return Arrays.stream(brickMatrixMap.get(currentOrientation)).map(int[]::clone).toArray(int[][]::new);
    }

    public boolean move(Direction direction) {
        if (isFreeze)
            return false;

        switch (direction) {
            case RIGHT:
                if (currentCoordinate.getCurrentX() == Setting.MAX_X)
                    return false;
                currentCoordinate.moveRight();
                break;
            case LEFT:
                if (currentCoordinate.getCurrentX() == 0)
                    return false;
                currentCoordinate.moveLeft();
                break;
            case DOWN:
                if (currentCoordinate.getCurrentY() == Setting.MAX_Y) {
                    return false;
                }
                currentCoordinate.moveDown();
                break;
        }
        return true;
    }

    public boolean rotate(RotateDirection direction) {
        if (availableOrientations.isEmpty() || isFreeze)
            return false;

        try {
            switch (direction) {
                case RIGHT:
                    availableOrientations.add(currentOrientation);
                    currentOrientation = availableOrientations.poll();
                    break;
                case LEFT:
                    Orientation temp = availableOrientations.pollLast();
                    availableOrientations.add(currentOrientation);
                    currentOrientation = temp;
                    break;
            }
            return true;
        } catch (Exception e) {
            System.err.println(Arrays.toString(e.getStackTrace()));
            return false;
        }
    }

    public void freeze() {
        isFreeze = true;
    }

    @Override
    public String toString() {
        return "[" +
                "currentOrientation=" + currentOrientation +
                ", name='" + name + '\'' +
                ", color=" + color +
                ", isFreeze=" + isFreeze +
                ", currentX=" + currentCoordinate.getCurrentX() +
                ", currentY=" + currentCoordinate.getCurrentY() +
                ']';
    }

    public String getCurrentMatrixString() {
        StringBuilder result = new StringBuilder();
        int[][] matrix = getBrickMatrix();
        for (int i = 0; i < matrix.length; i++) {
            if (i == matrix.length - 1)
                result.append(Arrays.toString(matrix[i]));
            else
                result.append(Arrays.toString(matrix[i]) + "\n");
        }
        return result.toString();
    }

    @Override
    public FatherBrick clone(){
        try {
            FatherBrick clonedBrick = (FatherBrick) super.clone();
            clonedBrick.availableOrientations = (LinkedList<Orientation>) this.availableOrientations.clone();
            clonedBrick.currentCoordinate = this.currentCoordinate.clone();
            return clonedBrick;
        } catch (Exception e) {
            return null;
        }
    }
}
