package Model;

import java.awt.image.BufferedImage;


public class Tile {
    public BufferedImage image;
    private boolean collision = false;

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }
}
