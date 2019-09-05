package building;

import geometry.Element3D;
import geometry.Point3D;
import geometry.Volume;

public class Furniture extends Element3D {
    private String name;
    private Point3D position;
    private Volume volume;

    @Override
    public Volume getVolume() {
        return volume;
    }

    @Override
    public Point3D getPosition() {
        return position;
    }
}
