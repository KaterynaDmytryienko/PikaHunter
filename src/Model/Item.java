package Model;

import View.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;

public class Item implements Serializable {

    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public BufferedImage back;
    private String name;
    private int type;
    private boolean collision = false;

    public boolean isCollision() {
        return collision;
    }

    public void setCollision(boolean collision) {
        this.collision = collision;
    }

    private int worldX;
    private int worldY;

    public int getWorldX() {
        return worldX;
    }

    public void setWorldX(int worldX) {
        this.worldX = worldX;
    }

    public int getWorldY() {
        return worldY;
    }

    public void setWorldY(int worldY) {
        this.worldY = worldY;
    }

    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // solid area for an object
    private int solidAreaDefaultX = 0;
    private int solidAreaDefaultY = 0;

    public int getSolidAreaDefaultX() {
        return solidAreaDefaultX;
    }

    public void setSolidAreaDefaultX(int solidAreaDefaultX) {
        this.solidAreaDefaultX = solidAreaDefaultX;
    }

    public int getSolidAreaDefaultY() {
        return solidAreaDefaultY;
    }

    public void setSolidAreaDefaultY(int solidAreaDefaultY) {
        this.solidAreaDefaultY = solidAreaDefaultY;
    }

    private Entity currentWeapon;
    private Entity currentShield;
    private String description = "";
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0); //entity attack area


    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.back = image;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //ITEM ATTRIBUTES
    private int attackValue;
    private int defenceValue;

    public int getAttackValue() {
        return attackValue;
    }

    public void setAttackValue(int attackValue) {
        this.attackValue = attackValue;
    }

    public int getDefenceValue() {
        return defenceValue;
    }

    public void setDefenceValue(int defenceValue) {
        this.defenceValue = defenceValue;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Entity getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Entity currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Entity getCurrentShield() {
        return currentShield;
    }

    public void setCurrentShield(Entity currentShield) {
        this.currentShield = currentShield;
    }

    public void draw(Graphics2D g2, GamePannel gp){
        int screenX = worldX - gp.player.worldx + gp.player.screenx;
        int screenY = worldY - gp.player.worldy + gp.player.screeny;

        if(worldX + gp.playerSize > gp.player.worldx - gp.player.screenx && worldX - gp.playerSize < gp.player.worldx + gp.player.screenx &&  // creating a boundary for drawing a tile
                worldY + gp.playerSize > gp.player.worldy - gp.player.screeny && worldY - gp.playerSize <  gp.player.worldy + gp.player.screeny){
            g2.drawImage(back, screenX, screenY, gp.playerSize, gp.playerSize, null);
        }
    }
}
