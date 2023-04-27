package Model;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int worldx, worldy;
    public int speed;

    public BufferedImage front;
    public BufferedImage right;
    public BufferedImage left;
    public BufferedImage back;
    public String direction;
    private Coordinates coordinates;

    public Rectangle solidArea; // class for abstract rectangle

    public int solidAreaDefaultX, solidAreaDefaultY;
    public boolean collisionOn = false;

    public int getX() {
        return worldx;
    }

    public int getY() {
        return worldy;
    }
}
