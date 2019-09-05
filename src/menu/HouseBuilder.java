package menu;

import building.Door;
import building.House;
import building.Room;
import building.Wall;
import geometry.Axe;
import geometry.Point3D;
import ui.Menu;
import ui.Picker;

import java.util.ArrayList;
import java.util.Scanner;

public class HouseBuilder {
    static private Scanner scanner = new Scanner(System.in);

    public HouseBuilder(House house) {
        this.house = house;
    }

    public HouseBuilder(String name) {
        this(new House());
        house.setName(name);
    }
    public HouseBuilder() {
        this("Simple House");
    }

    private House house;

    public House getHouse() {
        return house;
    }

    void createRoom() {
        Picker<Room> roomPicker = new Picker<>("Choose a room to which attach the new room...");
        roomPicker.setCancellable(true);
        for (var room : house.getRooms()) {
            if (room.getDoors().size() < 4) {
                roomPicker.add(room.getName(), room);
            }
        }
        Room targetRoom = roomPicker.show();
        if (targetRoom == null)
            return;

        ArrayList<Wall> walls = new ArrayList<>() {{
            add(Wall.BACK);
            add(Wall.RIGHT);
            add(Wall.FRONT);
            add(Wall.LEFT);
        }};
        for (var door : targetRoom.getDoors()) {
            walls.remove(door.getWall());
        }

        Picker<Wall> wallPicker = new Picker<>("Choose to which wall you desire attach the room...");
        for (var side : walls) {
            wallPicker.add(side.name(), side);
        }
        Wall wall = wallPicker.show();

        System.out.print("Choose a name for the new room: ");
        final var name = scanner.nextLine();
        System.out.println();

        Room newRoom = house.createRoom(name);
        Door targetDoor = targetRoom.addDoor(Door.DEFAULT_SIZE, wall);
        Door newDoor = newRoom.addDoor(Door.DEFAULT_SIZE, wall.getOppositeWall());
        Door.connect(targetDoor, newDoor);

        Point3D pos = targetRoom.getPosition().clone();
        if (wall.getAxe() == Axe.Y)
            pos.setX(pos.getX() + targetRoom.getVolume().getX() * wall.getX() - newRoom.getVolume().getX() * (1 - wall.getX()));
        else if (wall.getAxe() == Axe.X)
            pos.setY(pos.getY() + targetRoom.getVolume().getY() * wall.getY() - newRoom.getVolume().getY() * (1 - wall.getY()));
        newRoom.setPosition(pos);

    }

    public void removeRoom() {
        Picker<Room> picker = new Picker<>("Choose a room...");
        picker.setCancellable(true);
        for (var room : house.getRooms()) {
            if (room.getDoors().size() == 1 && room != house.getEntrance())
                picker.add(room.getName(), room);
        }
        var room = picker.show();
        if (room != null)
            house.removeRoom(room);
    }

    public void changeName() {
        System.out.print("New name: ");
        var name = scanner.nextLine();
        house.setName(name);
        System.out.println();
    }

    public void show() {
        Menu menu = new Menu("Building a house...") {{
            add("Create a room", () -> createRoom());
            add("Remove a room", () -> removeRoom());
            add("Change house name", () -> changeName());
        }};
        menu.setCancellable(true);
        while (menu.show())
            System.out.println("true");
    }
}
