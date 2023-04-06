package Model;

import java.awt.image.BufferedImage;

public class Entity {
    public int x, y;
    public int speed;

    public BufferedImage front; //image with an accessible buffer of image data
    public String direction;

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
