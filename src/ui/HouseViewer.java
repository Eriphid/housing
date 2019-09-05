package ui;

import building.House;

import javax.swing.*;
import java.awt.*;
import java.util.Timer;
import java.util.TimerTask;

import geometry.Point3D;

public class HouseViewer extends JPanel {
    House house;

    public HouseViewer(House house) throws HeadlessException {
        this.house = house;

        var timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                repaint();
            }
        }, 0, 1000 / 60);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        ((Graphics2D) g).setBackground(Color.black);
        g.clearRect(0, 0, getWidth(), getHeight());

        Point3D min = new Point3D();
        Point3D max = new Point3D();

        for (var room : house.getRooms()) {
            var pos = room.getPosition();
            var vol = room.getVolume();
            if(pos == null || vol == null)
                continue;
            if (min.getX() > pos.getX())
                min.setX(pos.getX());
            if (min.getY() > pos.getY())
                min.setY(pos.getY());
            if (min.getZ() > pos.getZ())
                min.setZ(pos.getZ());
            if (max.getX() < pos.getX() + vol.getX())
                max.setX(pos.getX() + vol.getX());
            if (max.getY() < pos.getY() + vol.getY())
                max.setY(pos.getY() + vol.getY());
            if (max.getZ() < pos.getZ() + vol.getZ())
                max.setZ(pos.getZ() + vol.getZ());
        }
        final int padding = 30;
        g.translate(padding, padding);

        var xScale = (getWidth() - padding * 2.0) / (max.getX() - min.getX());
        var yScale = (getHeight() - padding * 2.0) / (max.getY() - min.getY());
        var scale = Math.min(xScale, yScale);
        ((Graphics2D) g).scale(scale, scale);
        g.translate(-min.getX(), -min.getY());
        g.setFont(new Font("Arial", Font.BOLD, 20));

        final int strokeWidth = 2;
        ((Graphics2D) g).setStroke(new BasicStroke(strokeWidth));

        for (var room : house.getRooms()) {
            var pos = room.getPosition();
            var vol = room.getVolume();
            g.setColor(room.getColor());
            g.fillRect(pos.getX(), pos.getY(), vol.getX(), vol.getY());
            g.setColor(Color.WHITE);
            g.drawRect(pos.getX(), pos.getY(), vol.getX(), vol.getY());
            g.drawString(room.getName(), pos.getX() + 20, pos.getY() + 50);

            for (var door : room.getDoors()) {
                var transform = ((Graphics2D) g).getTransform();

                final var wall = door.getWall();
                final var anchor = wall.getAnchor();

                Point3D position = room.getPosition().clone();
                position.setX(position.getX() + room.getVolume().getX() * anchor.getX());
                position.setY(position.getY() + room.getVolume().getY() * anchor.getY());
                var doorVolume = door.getVolume();

                g.setColor(door.getColor());
                g.translate(position.getX(), position.getY());
                ((Graphics2D) g).rotate(Math.PI * wall.getRotation() / 180);
                g.translate(door.getOffset(), strokeWidth / 2);
                g.fillRect(0, 0, doorVolume.getX(), doorVolume.getY());

                ((Graphics2D) g).setTransform(transform);
            }
        }
    }
}
