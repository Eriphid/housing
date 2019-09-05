package building;

import geometry.Axe;
import geometry.Point2D;

public enum Wall {
    BACK,
    RIGHT,
    FRONT,
    LEFT;

    public Wall getOppositeWall() {
        switch (this) {
            case BACK:
                return FRONT;
            case FRONT:
                return BACK;
            case RIGHT:
                return LEFT;
            case LEFT:
                return RIGHT;
        }

        return null;
    }

    public Point2D getAnchor() {
        switch (this) {
            case BACK:
                return new Point2D(0, 0);
            case RIGHT:
                return new Point2D(1, 0);
            case FRONT:
                return new Point2D(1, 1);
            case LEFT:
                return new Point2D(0, 1);
        }
        return null;
    }

    public int getRotation() {
        switch (this) {
            case BACK:
                return 0;
            case RIGHT:
                return 90 * 1;
            case FRONT:
                return 90 * 2;
            case LEFT:
                return 90 * 3;
        }
        return 0;
    }

    public int getX() {
        if (this == RIGHT) {
            return 1;
        }
        return 0;
    }

    public int getY() {
        if (this == FRONT) {
            return 1;
        }
        return 0;
    }

    public Axe getAxe(){
        switch (this){
            case BACK:
            case FRONT:
                return Axe.X;
            case RIGHT:
            case LEFT:
                return Axe.Y;
        }
        return null;
    }
}
