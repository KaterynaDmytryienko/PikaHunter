package Model;

import View.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Item {
    public BufferedImage image;
    public BufferedImage image2;
    public BufferedImage image3;
    public String name;
    public boolean collision = false;
    public int worldX;
    public int worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48); // solid area for an object
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePannel gp){
        int screenX = worldX - gp.player.worldx + gp.player.screenx;
        int screenY = worldY - gp.player.worldy + gp.player.screeny;

        if(worldX + gp.playerSize > gp.player.worldx - gp.player.screenx && worldX - gp.playerSize < gp.player.worldx + gp.player.screenx &&  // creating a boundary for drawing a tile
                worldY + gp.playerSize > gp.player.worldy - gp.player.screeny && worldY - gp.playerSize <  gp.player.worldy + gp.player.screeny){
            g2.drawImage(image, screenX, screenY, gp.playerSize, gp.playerSize, null);
        }
    }
}
