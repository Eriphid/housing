package geometry;

import java.util.Objects;

public class Volume extends Surface {
    private int z;

    public int getZ() {
        return z;
    }

    public void setZ(int z) {
        this.z = z;
    }

    public Volume() {

    }

    public Volume(int x, int y, int z) {
        super(x, y);
        this.z = z;
    }

    public Volume(int x, int y, int z, Unit unit) {
        super(x, y, unit);
        this.z = z;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Volume volume = (Volume) o;
        return z == volume.z;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), z);
    }

    @Override
    public int getValue() {
        return super.getValue() * z;
    }
}
