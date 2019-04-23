package Components.Bricks;

import Enums.Direction;
import Enums.RotateDirection;

public interface Brick {
    //methods
    int[][] getBrickMatrix();

    boolean move(Direction direction);

    boolean rotate(RotateDirection direction);

    void freeze();
}
