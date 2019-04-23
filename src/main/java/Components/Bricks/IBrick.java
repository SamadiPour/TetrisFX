package Components.Bricks;

import Components.Specification.Coordinate;
import Components.Specification.Dimension;
import Enums.Orientation;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.LinkedList;

public final class IBrick extends FatherBrick {
    private static HashMap<Orientation, int[][]> _brickMatrixMap;
    private static LinkedList<Orientation> _availableOrientations;
    private static HashMap<Orientation, Dimension> _dimensionMap;

    static {
        _brickMatrixMap = new HashMap<>();
        _availableOrientations = new LinkedList<>();
        _dimensionMap = new HashMap<>();

        //available orientation for brick
        _availableOrientations.add(Orientation.EAST);

        //different brick shapes in different orientation
        _brickMatrixMap.put(Orientation.NORTH, new int[][]{
                {0, 0, 0, 0},
                {1, 1, 1, 1},
                {0, 0, 0, 0},
                {0, 0, 0, 0}
        });

        _brickMatrixMap.put(Orientation.EAST, new int[][]{
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0},
                {0, 1, 0, 0}
        });

        //different dimension
        _dimensionMap.put(Orientation.NORTH, new Dimension(4, 1));
        _dimensionMap.put(Orientation.EAST, new Dimension(1, 4));
    }

    //brick constructor that pass all static data to father brick
    public IBrick() {
        super("I", Color.web("#3498db"), Orientation.NORTH,
                _brickMatrixMap, _dimensionMap, _availableOrientations,
                false, new Coordinate(5, 0));
    }
}
