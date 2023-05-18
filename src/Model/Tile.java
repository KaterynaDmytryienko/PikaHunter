package Model;

import java.awt.image.BufferedImage;


public class Tile {
    private Coordinates coordinates;
    public BufferedImage image;
    private boolean collision = false;

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
