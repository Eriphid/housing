package geometry;

public class Point2D implements Cloneable {
    private int x;
    private int y;

    public Point2D() {
    }

    public Point2D(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public Point2D clone() {
        return new Point2D(x, y);
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
