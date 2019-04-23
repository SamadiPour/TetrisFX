package Components.Specification;

public class Coordinate implements Cloneable {
    int currentX;
    int currentY;

    public Coordinate(int currentX, int currentY) {
        this.currentX = currentX;
        this.currentY = currentY;
    }

    public int getCurrentX() {
        return currentX;
    }

    public int getCurrentY() {
        return currentY;
    }

    public void moveDown() {
        currentY++;
    }

    public void moveRight() {
        currentX++;
    }

    public void moveLeft() {
        currentX--;
    }

    @Override
    public Coordinate clone() {
        try {
            return (Coordinate) super.clone();
        } catch (Exception e) {
            return null;
        }
    }
}
