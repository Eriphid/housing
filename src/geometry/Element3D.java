package geometry;

public abstract class Element3D {
    public abstract Point3D getPosition();
    public abstract Volume getVolume();

    public boolean collide(Element3D o) {
        // Position & Size of the other element
        final var opos = o.getPosition();
        final var osiz = o.getVolume();

        // Position & Size of this element
        final var pos = getPosition();
        final var siz = getVolume();

        return opos.getX() < pos.getX() + siz.getX() && opos.getX() + osiz.getX() > pos.getX()
                && opos.getY() < pos.getY() + siz.getY() && opos.getY() + osiz.getY() > pos.getY()
                && opos.getZ() < pos.getZ() + siz.getZ() && opos.getZ() + osiz.getZ() > pos.getZ();
    }
}
