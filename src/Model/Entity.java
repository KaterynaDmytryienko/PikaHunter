package Model;

import View.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public boolean collision;  //ABSTRACT CLASS
    GamePannel gp;
    public int speed;

    public BufferedImage front;
    public BufferedImage right;
    public BufferedImage left;
    public BufferedImage back;
    public String direction;
    public Rectangle solidArea = new Rectangle(); // class for abstract rectangle
    public int worldX;
    public int worldY;

    public int worldx, worldy;

    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    public boolean collisionOn = false;

    //CHARACTER STATUS
    private int maxLife;
    private int life;
    private String name;
    public int actionLockCounter = 0;

    public Entity(GamePannel gp){
        this.gp = gp;
    }

    //CREATING CONSTRUCTOR

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAction() {
    }


    public void update(){
        setAction();
        collisionOn = false;

        if (collisionOn == false){


            switch (direction){
                case "up":
                    worldY -= speed;
                    break;
                case "down":
                    worldY += speed; break;
                case "left":
                    worldX -= speed; break;
                case "right":
                    worldX += speed; break;
            }
        }

    }

    public void draw(Graphics2D g2, GamePannel gp) {
        BufferedImage image = null;

        int screenX = worldX - gp.player.worldx + gp.player.screenx;
        int screenY = worldY - gp.player.worldy + gp.player.screeny;

        if (worldX + gp.playerSize > gp.player.worldx - gp.player.screenx && worldX - gp.playerSize < gp.player.worldx + gp.player.screenx &&  // creating a boundary for drawing a tile
                worldY + gp.playerSize > gp.player.worldy - gp.player.screeny && worldY - gp.playerSize < gp.player.worldy + gp.player.screeny) {
            switch (direction){
                case "up":
                    image = front;
                    break;

                case "down":
                    image = back;
                    break;
                case "right":
                    image = front;
                    break;
                case "left":
                    image = back;
                    break;
            }
            g2.drawImage(image, screenX, screenY, gp.playerSize, gp.playerSize, null);
        }
    }

}
