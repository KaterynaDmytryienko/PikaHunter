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

    //CHARACTER STATUS
    private int maxLife;
    private int life;

    public int getMaxLife() {
        return maxLife;
    }

    public void setMaxLife(int maxLife) {
        this.maxLife = maxLife;
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getX() {
        return worldx;
    }

    public int getY() {
        return worldy;
    }
}
