package Model;

import View.GamePannel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity{
    KeyHandler keyHandler;
    public final int screenx;
    public final int screeny;
    public int keyAmount = 0;
    public int elixirAmount = 0;
    public ArrayList<Entity> inventory = new ArrayList<>();
    public final int inventorySize = 20;

    public Player(GamePannel gp, KeyHandler keyHandler) throws IOException {
        super(gp);
        screenx = gp.screenWidth / 2 - (gp.playerSize /2); /* returns the halfway point of the screen
                                             */
        screeny = gp.screenHeight / 2 - (gp.playerSize /2);


        solidArea = new Rectangle(); // setting collision area for collision detection

        solidArea.x = 10;
        solidArea.y = 20;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        solidArea.width = 30;
        solidArea.height = 32;
        this.keyHandler = gp.getKeyHandler();
        attackArea.width = 36;
        attackArea.height = 36;

        setDefault();
        getPlayerImage();
        getPlayerAttackImages();
        setItems();
    }

    /**
     * Setting default values for an instance.
     */
    public void setDefault() throws IOException {
       this.worldx = gp.playerSize * 23;
       this.worldy = gp.playerSize * 21;

        speed = 3;
        direction = "left";
       //PLAYER STATUS where halfHeart is 1 life, 6 half hearts are 3 lives  (two lives - one heart)
        setLevel(1);
        setMaxLife(6);
        setLife(getMaxLife());
        setStrength(1);
        setDexterity(1);
//        setExp(0);
        setCurrentWeapon(new Sword(gp));
        setCurrentShield(new Shield(gp));
        setAttack(getAttackVariable());
        setDefense(getDefenseVariable());
    }

    public void setItems(){
        inventory.add(getCurrentWeapon());
        //shield
        inventory.add(getCurrentShield());
    }
    public int getAttackVariable(){
        return setAttack(getStrength()*getCurrentWeapon().getAttackValue());
    }

    public int getDefenseVariable(){
        return setDefense(getDexterity()* getCurrentShield().getDefenceValue());
    }
    /**
     * Gets images of an instance.
     */
    public void getPlayerImage(){
        try{
            front = ImageIO.read(getClass().getResourceAsStream("/img/frontfront.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/img/right1.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/img/left1.png"));
            back = ImageIO.read(getClass().getResourceAsStream("/img/back.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public void getPlayerAttackImages() throws IOException {
        frontWithSword = ImageIO.read(getClass().getResourceAsStream("/img/frontWithSword.png"));
        backWithSword = ImageIO.read(getClass().getResourceAsStream("/img/backWithSword.png"));
        leftWithSword = ImageIO.read(getClass().getResourceAsStream("/img/leftWithSword.png"));
        rightWithSword = ImageIO.read(getClass().getResourceAsStream("/img/rightWithSword.png"));
    }
    /**
     * Updates position of a Player instance on a screen and changes variable direction.
     */
    public void update(){

        if(gp.getKeyHandler().pressedSpace){
            System.out.println("Im attacking");
            setAttacking(true);
            attacking();
        }

        else if (keyHandler.pressedUp){
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
        gp.collisionController.checkTile(this);

        //CHECK EVENT
        gp.eventHandler.checkEvent();

        //CHECK OBJECT COLLISION
        int objectIndex = gp.collisionController.checkObject(this, true);
        pickUpObject(objectIndex);

        //CHECK MONSTER COLLISION
        int monsterIndex = gp.collisionController.checkEntity(this, gp.monster);
        interactMonster(monsterIndex);


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

        if(isInvincible()){
            invincibleCounter++;
            if(invincibleCounter > 60){
                setInvincible(false);
                invincibleCounter = 0;
            }
        }

    }

    public void attacking() {
        spriteCounter++;
        if (spriteCounter < 25) {
            int currentWorldX = worldX;
            int currentWorldY = worldY;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldY -= attackArea.height;
                    break;
                case "down":
                    worldY += attackArea.height;
                    break;
                case "left":
                    worldX -= attackArea.width;
                    break;
                case "right":
                    worldX += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width; //change size of player solid area to attack area
            solidArea.height = attackArea.height;

            //check monster collision with updated worldX worldY and solidArea
            int monsterIndex = gp.collisionController.checkEntity(this, gp.monster);
            damageMonster(monsterIndex);

            //restoring the original data
            worldX = currentWorldX;
            worldY = currentWorldY;

            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        else {
            spriteCounter = 0;
            setAttacking(false);
        }

    }
    public void pickUpObject(int i){
       if(i != 999){
           String objectName = gp.item[i].name;

           switch (objectName){
               case "key":
                   keyAmount ++;
                   gp.item[i] = null;
                   break;

               case "chest":
                   if(keyAmount > 0){
                       keyAmount--;
                       elixirAmount++;
                       gp.item[i] = null;
                   }
                   break;
           }
       }
    }

    public void interactMonster(int index){
        if(index != 999){
            if(isInvincible() == false) {// player receives damage only if he is not invincible
                setLife(getLife()-1);
                setInvincible(true);


            }

        }

    }

    public void damageMonster(int i){
        if(i != 999){
           if(gp.monster[i].isInvincible() == false){
               gp.monster[i].setLife(gp.monster[i].getLife()-1);
               gp.monster[i].setInvincible(true);

               if(gp.monster[i].getLife() <= 0){
                   gp.monster[i] = null;
               }
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
        switch (direction) {
            case "up":
                if(isAttacking() == false){
                    image = back;
                }
                if(isAttacking()){
                    image = backWithSword;
                }
                break;

            case "left":
                if(isAttacking() == false){
                    image = left;
                }
                if(isAttacking()){
                    image = leftWithSword;
                }
                break;

            case "right":
                if(isAttacking() == false){
                    image = right;
                }
                if(isAttacking()){
                    image = rightWithSword;
                }
                break;

            case "down":
                if(isAttacking() == false){
                    image = front;
                }
                if(isAttacking()){
                    image = frontWithSword;
                }
                break;
        }

        if(isInvincible()){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); //setting opacity level for player image
        }

        g2.drawImage(image, screenx, screeny, gp.playerSize, gp.playerSize, null);

        //Reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }


}
