package building;

import geometry.Element3D;
import geometry.Point3D;
import geometry.Volume;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Room extends Element3D {
    public String getName() {
        return name;
    }

    private String name;
    private Volume volume = new Volume(200, 200, 200);
    private Point3D position;
    private Color color = Color.GRAY;

    ArrayList<Furniture> furnitures = new ArrayList<>();
    ArrayList<Door> doors = new ArrayList<>();

    public List<Room> getRooms() {
        return doors.stream().map(door -> door.getRoom()).collect(Collectors.toList());
    }

    public final ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }

    public Room(String name) {
        this.name = name;
    }

    public ArrayList<Door> getDoors() {
        return doors;
    }

    boolean addFurniture(Furniture furniture) {
        return furnitures.add(furniture);
    }

    public int getWallSize(Wall wall) {
        switch (wall) {
            case BACK:
            case FRONT:
                return volume.getX();
            case LEFT:
            case RIGHT:
                return volume.getY();
            default:
                return -1;
        }
    }

    public void setPosition(Point3D position) {
        this.position = position;
    }

    @Override
    public Point3D getPosition() {
        return position;
    }

    @Override
    public Volume getVolume() {
        return volume;
    }

    public Door addDoor(Volume size, Wall wall, int offset) {
        Door door = new Door(this);
        door.setWall(wall);
        door.setVolume(size);
        door.setOffset(offset);
        if (!doors.add(door))
            return null;
        return door;
    }

    public Door addDoor(Volume size, Wall wall) {
        final int x = (getWallSize(wall) - size.getX()) / 2;
        return addDoor(size, wall, x);
    }

    public boolean removeDoor(Door door){
        return doors.remove(door);
    }

    public void attach(Door ownedDoor, Door otherDoor){

    }

    public void disconnect() {
        for (var door : getDoors()) {
            var connectedDoor = door.getConnectedDoor();
            if (connectedDoor != null)
                connectedDoor.remove();
        }
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
