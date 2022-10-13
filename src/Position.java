import java.awt.*;
import java.util.Iterator;

public class Position implements Iterable{
    private int X;
    private int Y;

    Position (int X, int Y) {
        this.X = X;
        this.Y = Y;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public boolean equals(Position otherPosition) {
        return X == otherPosition.X && Y == otherPosition.Y;
    }

    public Position add(Position otherPosition) {
        return new Position(X + otherPosition.X, Y + otherPosition.Y);
    }
    public GridLayout toGridLayout () { return new GridLayout(Y+1,X+1); }

    @Override
    public PositionIterator iterator() {return new PositionIterator(this);}

    @Override
    public String toString() {
        return "X: " + X + " Y: " + Y;
    }
}

class PositionIterator implements Iterator {
    Position highestPosition;
    Position currentPosition;
    boolean hasNext;
    PositionIterator (Position highestPosition) {
        this.highestPosition = highestPosition;
        this.currentPosition = new Position(-1,0);
        this.hasNext = true;
    }

    @Override
    public boolean hasNext() {System.out.println(currentPosition);
        return !highestPosition.equals(currentPosition);
    }

    @Override
    public Position next() {
        if (currentPosition.getX() < highestPosition.getX()) {
            currentPosition = currentPosition.add(new Position(1,0));
            return currentPosition;
        }
        else if (currentPosition.getY() < highestPosition.getY()) {
            currentPosition = currentPosition.add(new Position(-currentPosition.getX(),1));
            return currentPosition;
        }
        hasNext = false;
        return null;
    }
}
