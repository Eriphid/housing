package building;

import geometry.Point3D;

import java.util.HashSet;

public class House {
    private String name;
    private HashSet<Room> rooms = new HashSet<>();

    private Room entrance = createRoom("Entrance");

    public House() {
        entrance.setPosition(new Point3D(0, 0, 0));
        entrance.addDoor(Door.DEFAULT_SIZE, Wall.FRONT);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Room getEntrance() {
        return entrance;
    }

    public final HashSet<Room> getRooms() {
        return rooms;
    }

    public Room createRoom(String name){
        Room room = new Room(name);
        rooms.add(room);
        return room;
    }

    public boolean removeRoom(Room room){
        room.disconnect();
        return rooms.remove(room);
    }
}
