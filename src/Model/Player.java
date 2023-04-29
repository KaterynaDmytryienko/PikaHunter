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

    public int keyAmount = 0;
    public int elixirAmount = 0;


    public Player(GamePannel gameP, KeyHandler keyHandler){
        screenx = gameP.screenWidth / 2 - (gameP.playerSize /2); /* returns the halfway point of the screen
                                             */
        screeny = gameP.screenHeight / 2 - (gameP.playerSize /2);


        solidArea = new Rectangle(); // setting collision area for collision detection

        solidArea.x = 10;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 32;
        this.gameP = gameP;
        this.keyHandler = gameP.getKeyHandler();
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
       //PLAYER STATUS where halfHeart is 1 life, 6 half hearts are 3 lives  (two lives - one heart)
        setMaxLife(6);
        setLife(getMaxLife());

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

        collisionOn = false;
        gameP.collisionController.checkTile(this);

        //CHECK EVENT
        gameP.eventHandler.checkEvent();

        //CHECK OBJECT COLLISION
        int objectIndex = gameP.collisionController.checkObject(this, true);
        pickUpObject(objectIndex);


        //IF COLLISION == FALSE, player can move
        if(!collisionOn && (keyHandler.pressedUp || keyHandler.pressedDown || keyHandler.pressedLeft
                || keyHandler.pressedRight)){
            switch (direction) {
                case "up" -> this.worldy -= speed;
                case "down" -> this.worldy += speed;
                case "left" -> this.worldx -= speed;
                case "right" -> this.worldx += speed;
            }
        }

    }

    public void pickUpObject(int i){
       if(i != 999){
           String objectName = gameP.item[i].name;

           switch (objectName){
               case "key":
                   keyAmount ++;
                   gameP.item[i] = null;
                   break;

               case "chest":
                   if(keyAmount > 0){
                       keyAmount--;
                       elixirAmount++;
                       gameP.item[i] = null;
                   }
                   break;
           }
       }
    }

    /**
     * Draws Player instance on a screen and changes images depending on direction of a Player instance.
     *
     * @param g2
     */
    public void draw(Graphics2D g2) {
        BufferedImage image = switch (direction) {
            case "up" -> back;
            case "left" -> left;
            case "right" -> right;
            case "down" -> front;
            default -> null;
        };

        ;

        g2.drawImage(image, screenx, screeny, gameP.playerSize, gameP.playerSize, null);
    }


}
