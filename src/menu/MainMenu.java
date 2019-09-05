package menu;

import building.House;
import ui.HouseViewer;
import ui.Menu;

import javax.swing.*;

public class MainMenu {
    private JFrame viewer;
    private HouseBuilder builder = new HouseBuilder();
    private House house = builder.getHouse();

    private Menu menu = new Menu("Main menu") {{
        add("House Modelling", () -> modelizeHouse());
        add("House Viewing", () -> {
            viewer.setVisible(true);
            viewer.setTitle(house.getName());
        });
    }};

    public MainMenu() {
        viewer = new JFrame();
        viewer.setTitle(house.getName());
        viewer.setContentPane(new HouseViewer(house));
        viewer.setSize(500, 500);
    }

    private void modelizeHouse() {
        builder.show();
    }

    public void show() {
        for (; ; ) {
            menu.show();
        }
    }
}
