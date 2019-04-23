package Components.Bricks;

import Components.Specification.Coordinate;
import Components.Specification.Dimension;
import Enums.Orientation;
import javafx.scene.paint.Color;

import java.util.HashMap;
import java.util.LinkedList;

public final class OBrick extends FatherBrick {

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
                {0, 4, 4, 0},
                {0, 4, 4, 0},
                {0, 0, 0, 0}
        });

        //different dimension
        _dimensionMap.put(Orientation.NORTH, new Dimension(2, 2));
    }

    //brick constructor that pass all static data to father brick
    public OBrick() {
        super("O", Color.web("#f1c40f"), Orientation.NORTH,
                _brickMatrixMap, _dimensionMap, _availableOrientations,
                false, new Coordinate(5, 0));
    }
}
