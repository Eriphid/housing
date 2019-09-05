package geometry;

import java.util.Objects;

public class Surface {
    private int x;
    private int y;

    Unit unit = Unit.CM;

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }


    public Surface(){

    }

    public Surface(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Surface(int x, int y, Unit unit) {
        this.x = x;
        this.y = y;
        this.unit = unit;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Surface surface = (Surface) o;
        return x == surface.x &&
                y == surface.y &&
                unit == surface.unit;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, unit);
    }

    public int getValue() {
        return x * y;
    }

}
