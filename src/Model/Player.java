package Model;

import View.GamePannel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import javax.swing.*;
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

        solidArea = new Rectangle(10, 20, 30, 32); // setting collision area for collision detection

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

        speed = 3;
        direction = "left";
        this.healthPoints = 3;

    }

    /**
     * Gets images of an instance.
     */
    public void getPlayerImage(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/img/frontfront.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/img/right.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/img/Pikachu.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/img/back.png"));
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
            direction = "up";
        }

        else if(keyHandler.pressedDown){ // player coordinate Y increases by 4 px (player speed)
            direction = "down";
        }

        else if(keyHandler.pressedRight){
            direction = "right";
        }

        else if(keyHandler.pressedLeft){
            direction = "left";
        }

        //CHECK TILE COLLISION !!!!!!!!!!!!!!!!!!!!!!S
        collisionOn = false;
        gameP.collisionController.checkTile(this);

        //IF COLLISION == FALSE, player can move
        if(collisionOn == false){
            switch (direction){
                case "up":
                    this.worldy -= speed; break;
                case "down":
                    this.worldy += speed; break;
                case "left":
                    this.worldx -= speed; break;
                case "right":
                    this.worldx += speed; break;
            }
        }

    }

    /**
     * Draws Player instance on a screen and changes images depending on direction of a Player instance.
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = null;

        switch (direction){
            case "up":
               image = back; break;
            case "left":
            image = left; break;
            case "right":
                image = right; break;
            case "down":
                image = front; break;
        };

        g2.drawImage(image, screenx, screeny, gameP.playerSize, gameP.playerSize, null);
    }


}
