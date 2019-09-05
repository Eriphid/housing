package building;

import geometry.Volume;

import java.awt.*;

public class Door {
    private Room room;
    private Wall wall;
    private int offset;
    private Volume volume;
    private Door link;
    private Color color = new Color(0xaaaaff);

    final public static Volume DEFAULT_SIZE = new Volume(100, 10, 500);

    public Door(Room room, Wall wall, int offset, Volume volume) {
        this.room = room;
        this.wall = wall;
        this.offset = offset;
        this.volume = volume;
    }

    public Door(Room room) {
        this.room = room;
    }

    public Door getConnectedDoor() {
        return link;
    }

    public Wall getWall() {
        return wall;
    }

    public void setWall(Wall wall) {
        this.wall = wall;
    }

    public int getOffset() {
        return offset;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public Volume getVolume() {
        return volume;
    }

    public void setVolume(Volume volume) {
        this.volume = volume;
    }

    public Room getRoom() {
        return room;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public static void connect(Door a, Door b) {
        a.link = b;
        b.link = a;
    }

    public void remove() {
        if (room != null)
            room.removeDoor(this);
    }
}
