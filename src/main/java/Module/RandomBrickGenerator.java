package Module;

import Components.Bricks.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class RandomBrickGenerator {

    private final ArrayList<FatherBrick> brickList;
    private final LinkedList<FatherBrick> nextThree;

    public RandomBrickGenerator() {
        brickList = new ArrayList<>();
        nextThree = new LinkedList<>();

        //add all types of bricks to list
        brickList.add(new IBrick());
        brickList.add(new JBrick());
        brickList.add(new LBrick());
        brickList.add(new OBrick());
        brickList.add(new SBrick());
        brickList.add(new TBrick());
        brickList.add(new ZBrick());
        Collections.shuffle(brickList);

        //get three of them as [ - , - , - ]
        nextThree.add(brickList.remove(0));
        Collections.shuffle(brickList);
        nextThree.add(brickList.remove(0));
        Collections.shuffle(brickList);
        nextThree.add(brickList.remove(0));
        Collections.shuffle(brickList);
    }

    public FatherBrick getNextBrick() {
        //get next expected brick from [ - , - , - ]
        FatherBrick nextBrick = nextThree.removeFirst();
        FatherBrick nextBrickClone = nextBrick.clone();

        //get a random brick
        Collections.shuffle(brickList);
        nextThree.add(brickList.remove(0));

        //push back clone of the brick that is going to fall
        brickList.add(nextBrickClone);
        return nextBrick;
    }

    public FatherBrick[] peekNextThree() {
        return (FatherBrick[]) nextThree.toArray();
    }

}
