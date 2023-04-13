package Model;

import java.awt.image.BufferedImage;

public class Entity {
    public int worldx, worldy;
    public int speed;

    public BufferedImage front;
    public String direction;
    private Coordinates coordinates;

    public int getX() {
        return worldx;
    }

    public int getY() {
        return worldy;
    }
}
