package Model;

import View.GamePannel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;

public class Player extends Entity{
    GamePannel gameP;
    KeyHandler keyHandler;
    public Coordinates coordinates;
    private int healthPoints;

    public final int screenx;
    public final int screeny;
    private Item[] inventory;


    public Player(GamePannel gameP, KeyHandler keyHandler){
        screenx = gameP.screenWidth / 2 - (gameP.playerSize /2); /* returns the halfway point of the screen
                                             */
        screeny = gameP.screenHeight / 2 - (gameP.playerSize /2);
        this.gameP = gameP;
        this.keyHandler = keyHandler;
        setDefault();
        getPlayerImage();
    }

    /**
     * Setting default values for an instance.
     */
    public void setDefault(){
       this.worldx = gameP.playerSize * 23;
       this.worldy = gameP.playerSize * 21;

        speed = 5;
        direction = "front";
        this.healthPoints = 3;

    }

    /**
     * Gets images of an instance.
     */
    public void getPlayerImage(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/img/Pikachu.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * Updates position of a Player instance on a screen and changes variable direction.
     */
    public void update(){
        if (keyHandler.pressedUp){
            direction = "front";
            this.worldy -= speed;
        }

        else if(keyHandler.pressedDown){ // player coordinate Y increases by 4 px (player speed)
            direction = "front";
            this.worldy += speed;
        }

        else if(keyHandler.pressedRight){
            direction = "front";
            this.worldx += speed;
        }

        else if(keyHandler.pressedLeft){
            direction = "front";
            this.worldx -= speed;
        }

    }

    /**
     * Draws Player instance on a screen and changes images depending on direction of a Player instance.
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> front;
            case "left" -> front;
            case "right" -> front;
            case "down" -> front;
            default -> null;
        };

        g2.drawImage(front, screenx, screeny, gameP.playerSize, gameP.playerSize, null);
    }


}
