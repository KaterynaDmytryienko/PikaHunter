package Model;

import View.GamePannel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.Objects;


public class Player extends Entity{
    GamePannel gameP;
    KeyHandler keyHandler;
    private int healthPoints;

    public Player(GamePannel gameP, KeyHandler keyHandler){
        this.gameP = gameP;
        this.keyHandler = keyHandler;
        setDefault();
        getPlayerImage();
    }

    /**
     * setting default values for an instance
     */
    public void setDefault(){
        this.x = 100;
        this.y = 100;
        speed = 5;
        direction = "front";
        this.healthPoints = 3;

    }

    /**
     * gets images of an instance
     */
    public void getPlayerImage(){
        try{
            front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/img/Pikachu.png")));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    /**
     * updates position of a Player instance on a screen and changes variable direction
     */
    public void update(){
        if (keyHandler.pressedUp){
            direction = "front";
            this.y -= speed;
        }

        else if(keyHandler.pressedDown){ // player coordinate Y increases by 4 px (player speed)
            direction = "front";
            this.y += speed;
        }

        else if(keyHandler.pressedRight){
            direction = "front";
            this.x += speed;
        }

        else if(keyHandler.pressedLeft){
            direction = "front";
            this.x -= speed;
        }

    }

    /**
     * draws Player instance on a screen and changes images depending on direction of a Player instance
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

        g2.drawImage(front, x, y, gameP.PLAYER_SIZE, gameP.PLAYER_SIZE, null);
    }


}
