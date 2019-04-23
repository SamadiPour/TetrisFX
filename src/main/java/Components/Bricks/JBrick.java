package Components.Bricks;

import Components.Specification.Coordinate;
import Components.Specification.Dimension;
import Enums.Orientation;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.LinkedList;

public final class JBrick extends FatherBrick {
    private static HashMap<Orientation, int[][]> _brickMatrixMap;
    private static LinkedList<Orientation> _availableOrientations;
    private static HashMap<Orientation, Dimension> _dimensionMap;

    static {
        _brickMatrixMap = new HashMap<>();
        _availableOrientations = new LinkedList<>();
        _dimensionMap = new HashMap<>();

        //available orientation for brick
        _availableOrientations.add(Orientation.EAST);
        _availableOrientations.add(Orientation.SOUTH);
        _availableOrientations.add(Orientation.WEST);

        //different brick shapes in different orientation
        _brickMatrixMap.put(Orientation.NORTH, new int[][]{
                {0, 0, 0, 0},
                {0, 2, 0, 0},
                {0, 2, 2, 2},
                {0, 0, 0, 0}
        });

        _brickMatrixMap.put(Orientation.EAST, new int[][]{
                {0, 0, 0, 0},
                {0, 2, 2, 0},
                {0, 2, 0, 0},
                {0, 2, 0, 0}
        });

        _brickMatrixMap.put(Orientation.SOUTH, new int[][]{
                {0, 0, 0, 0},
                {2, 2, 2, 0},
                {0, 0, 2, 0},
                {0, 0, 0, 0}
        });

        _brickMatrixMap.put(Orientation.WEST, new int[][]{
                {0, 0, 2, 0},
                {0, 0, 2, 0},
                {0, 2, 2, 0},
                {0, 0, 0, 0}
        });

        //different dimension
        _dimensionMap.put(Orientation.NORTH, new Dimension(3, 2));
        _dimensionMap.put(Orientation.EAST, new Dimension(2, 3));
        _dimensionMap.put(Orientation.SOUTH, new Dimension(3, 2));
        _dimensionMap.put(Orientation.WEST, new Dimension(2, 3));
    }

    //brick constructor that pass all static data to father brick
    public JBrick() {
        super("J", Color.web("#227093"), Orientation.NORTH,
                _brickMatrixMap, _dimensionMap, _availableOrientations,
                false, new Coordinate(5, 0));
    }
}