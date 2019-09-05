package geometry;

public class Point3D extends Point2D {
    private int z;

    public Point3D() {
    }

    public Point3D(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    @Override
    public Point3D clone() {
        return new Point3D(getX(), getY(), getZ());
    }
}
