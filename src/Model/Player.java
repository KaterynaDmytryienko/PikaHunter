package Model;

import View.GamePannel;
import Controller.KeyHandler;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Logger;

public class Player extends Entity{
    private static final Logger logger = Logger.getLogger(Player.class.getName());
    KeyHandler keyHandler;
    public final int screenx;
    public final int screeny;
    private int keyAmount = 0;
    private int elixirAmount = 0;

    public int getKeyAmount() {
        return keyAmount;
    }

    public void setKeyAmount(int keyAmount) {
        this.keyAmount = keyAmount;
    }

    public int getElixirAmount() {
        return elixirAmount;
    }

    public void setElixirAmount(int elixirAmount) {
        this.elixirAmount = elixirAmount;
    }

    public ArrayList<Item> inventory = new ArrayList<>();
    public int inventorySize = 20;

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
        setMaxLife(6);
        setLife(getMaxLife());
        setStrength(1);
        setDexterity(1);
        setCurrentWeapon(new Sword());
        setCurrentShield(new Shield());
        setAttack(getAttackVariable());
        setDefense(getDefenseVariable());
    }

    /**
     * Method sets default position for a player.
     */
    public void setDefaultPositions(){
        this.worldx = gp.playerSize * 23;
        this.worldy = gp.playerSize * 21;
        logger.info("Default position of the player is set.");
    }

    /**
     * Method restores player`s life.
     */
    public void restoreLife(){
        setLife(getMaxLife());
        setInvincible(false);
    }

    /**
     * Method sets items to the inventory.
     */
    public void setItems(){
        inventory.clear();
        inventory.add(getCurrentWeapon());
        inventory.add(getCurrentShield());
        logger.info("Items in the inventory are set.");
    }

    /**
     * Method gets attack variable for the current weapon.
     * @return int
     */
    public int getAttackVariable(){
        attackArea = getCurrentWeapon().attackArea; //updating attack area depending on a weapon
        return setAttack(getStrength()*getCurrentWeapon().getAttackValue());
    }

    /**
     * Method gets defence variable for the current shield.
     * @return int
     */
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

    /** Methods gets player`s attack images for the attacking state.
     * @throws IOException
     */
    public void getPlayerAttackImages() throws IOException {
        if(getCurrentWeapon().getType() == 3){
            frontWithSword = ImageIO.read(getClass().getResourceAsStream("/img/frontWithSword.png"));
            backWithSword = ImageIO.read(getClass().getResourceAsStream("/img/backWithSword.png"));
            leftWithSword = ImageIO.read(getClass().getResourceAsStream("/img/leftWithSword.png"));
            rightWithSword = ImageIO.read(getClass().getResourceAsStream("/img/rightWithSword.png"));
        }

        if(getCurrentWeapon().getType() == 4){
            frontWithAxe = ImageIO.read(getClass().getResourceAsStream("/img/pikaWithAxeFront.png"));
            backWithAxe = ImageIO.read(getClass().getResourceAsStream("/img/pikaWithAxeBack.png"));
            leftWithAxe = ImageIO.read(getClass().getResourceAsStream("/img/pikaWithAxeLeft.png"));
            rightWithAxe = ImageIO.read(getClass().getResourceAsStream("/img/pikaWithAxeRight.png"));
        }


    }
    /**
     * Updates position of a Player instance on a screen and changes variable direction; checks current events
     * and collision.
     */
    public void update() throws IOException {

        if(gp.getKeyHandler().isPressedSpace()){
            setAttacking(true);
            attacking();
        }

        else if (keyHandler.isPressedUp()){
            direction = "up";
        }

        else if(keyHandler.isPressedDown()){ // player coordinate Y increases by 4 px (player speed)
            direction = "down";
        }

        else if(keyHandler.isPressedRight()){
            direction = "right";
        }

        else if(keyHandler.isPressedLeft()){
            direction = "left";
        }

        setCollisionOn(false);
        gp.getCollisionController().checkTile(this);

        //CHECK EVENT
        gp.getEventHandler().checkEvent();

        //CHECK OBJECT COLLISION
        int objectIndex = gp.getCollisionController().checkObject(this, true);
        pickUpObject(objectIndex);

        //CHECK MONSTER COLLISION
        int monsterIndex = gp.getCollisionController().checkEntity(this, gp.getMonster());
        interactMonster(monsterIndex);

        //CHECK INTERACTIVE TILE COLLISION
        int iTileIndex = gp.getCollisionController().checkItem(this, gp.getiTile());
        damageInteractiveTile(iTileIndex);

        //IF COLLISION == FALSE, player can move
        if(!isCollisionOn() && (keyHandler.isPressedUp() || keyHandler.isPressedDown() || keyHandler.isPressedLeft()
                || keyHandler.isPressedRight())){
            switch (direction) {
                case "up" -> this.worldy -= speed;
                case "down" -> this.worldy += speed;
                case "left" -> this.worldx -= speed;
                case "right" -> this.worldx += speed;
            }
        }

        //When player is invincible, monsters can not give him damage for a certain period of time.
        if(isInvincible()){
            logger.warning("Player is invincible!");
            invincibleCounter++;
            if(invincibleCounter > 60){
                setInvincible(false);
                invincibleCounter = 0;
            }
        }

        //When player do not have lives, state of a game becomes GAME OVER
       if(getLife() <= 0){
           gp.gameState = gp.getGameOverState();
       }
    }


    public int getCurrentWeaponSlot(){
        int currentWeaponSlot = 0;
        for( int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == getCurrentWeapon()){
                currentWeaponSlot = i;
            }
        }
        return currentWeaponSlot;
    }

    public int getCurrentShieldSlot(){
        int currentShieldSlot = 0;
        for( int i = 0; i < inventory.size(); i++){
            if(inventory.get(i) == getCurrentShield()){
                currentShieldSlot = i;
            }
        }
        return currentShieldSlot;
    }

    /**
     * Method allows player to attack monsters and interact with interactive tiles.
     */
    public void attacking() {
        spriteCounter++;
        if (spriteCounter < 25) {
            int currentWorldX = worldx;
            int currentWorldY = worldy;
            int solidAreaWidth = solidArea.width;
            int solidAreaHeight = solidArea.height;

            //Adjust player worldX/Y for the attackArea
            switch (direction) {
                case "up":
                    worldy -= attackArea.height;
                    break;
                case "down":
                    worldy += attackArea.height;
                    break;
                case "left":
                    worldx -= attackArea.width;
                    break;
                case "right":
                    worldx += attackArea.width;
                    break;
            }

            solidArea.width = attackArea.width; //change size of player solid area to attack area
            solidArea.height = attackArea.height;

            //check monster collision with updated worldx worldy and solidArea
            int monsterIndex = gp.getCollisionController().checkEntity(this, gp.getMonster());
            damageMonster(monsterIndex);

            //INTERACTIVE TILE
            int iTileIndex = gp.getCollisionController().checkItem(this, gp.getiTile());
            damageInteractiveTile(iTileIndex);

            //restoring the original data
            worldx = currentWorldX;
            worldy = currentWorldY;

            solidArea.width = solidAreaWidth;
            solidArea.height = solidAreaHeight;
        }
        else {
            spriteCounter = 0;
            setAttacking(false);
        }

    }

    /**
     * Method allows to pick up objects on the map and adds them to inventory.
     * @param i
     */
    public void pickUpObject(int i) {
        if (i != 999) {
            if (inventory.size() != inventorySize) {
                Item item = gp.items.get(i);
                if (item.getName().equals("key")) {
                    keyAmount++;
                    inventory.add(item);
                    logger.info("Picked up key.");
                    gp.items.remove(i);
                } else if (item.getName().equals("chest")) {
                    if (keyAmount > 0) {
                        keyAmount--;
                        for (int j = 0; j < inventory.size(); j++) {
                            if (inventory.get(j) != null && inventory.get(j).getName().equals("key")) {
                                inventory.remove(inventory.get(j));
                                break;
                            }
                        }
                        elixirAmount++;
                        Elixir elixir = new Elixir();
                        inventory.add(elixir);
                        gp.items.remove(i);
                    } else if (keyAmount == 0) {
                        logger.warning("You don't have a key!");
                    }
                } else if (item.getName().equals("axe")) {
                    inventory.add(item);
                    logger.info("Picked up axe.");
                    gp.items.remove(i);
                }
            }
        }
    }


    /**
     * Method sets action when monster touching a player.
     * @param index
     */
    public void interactMonster(int index){
        if(index != 999){
            if(isInvincible() == false) {// player receives damage only if he is not invincible
                setLife(getLife()-1);
                setInvincible(true);
            }
        }
    }

    /**
     * Method sets action for a player to give monsters damage.
     * @param i
     */
    public void damageMonster(int i){
        if(i != 999){
           if(gp.getMonster()[i].isInvincible() == false){
               if(getCurrentWeapon().getType() == 3) {
                   gp.getMonster()[i].setLife(gp.getMonster()[i].getLife() - 1);
                   logger.info("Damaging monster!");
               }
               else if (getCurrentWeapon().getType() == 4){
                   gp.getMonster()[i].setLife(gp.getMonster()[i].getLife() - 2);
                   logger.info("Damaging monster!");
               }
               gp.getMonster()[i].setInvincible(true);

               if(gp.getMonster()[i].getLife() <= 0){
                   gp.getMonster()[i] = null;
               }
           }
        }
    }

    /**
     * Method sets action to interact with interactive tiles.
     * @param index
     */
    public void damageInteractiveTile(int index){
        if(index!=999 && gp.getiTile()[index].isDestructible() && gp.getiTile()[index].isSuitableWeapon(this) && keyHandler.isPressedSpace()){
            gp.getiTile()[index] = null;
        }
    }

    /**
     * Method allows player select item from the inventory.
     * @throws IOException
     */
    public void selectItem() throws IOException {
        int itemIndex = gp.getUserInterface().getItemIndexOnSlot();

        if(itemIndex < inventory.size()){
            Item selectedItem = inventory.get(itemIndex);

            if(selectedItem.getType() == 3 || selectedItem.getType()== 4){
                setCurrentWeapon(selectedItem);
                setAttack(getAttack());
                getPlayerAttackImages();
            }
            if(selectedItem.getType() == 6) {
                setCurrentShield(selectedItem);
                setDefense(getDefense());
            }
            if(selectedItem.getType()==7){
                setLife(getMaxLife());
                logger.info("Player refilled his life!");
                inventory.remove(selectedItem);
            }
        }
    }
    /**
     * Draws Player instance on a screen and changes images depending on direction of a Player instance and his current state.
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
                    if(getCurrentWeapon().getType() == 3) {
                        image = backWithSword;
                    }
                    else if(getCurrentWeapon().getType() == 4){
                        image = backWithAxe;
                    }
                }
                break;

            case "left":
                if(isAttacking() == false){
                    image = left;
                }
                if(isAttacking()){
                    if(getCurrentWeapon().getType() == 3) {
                        image = leftWithSword;
                    }
                    else if(getCurrentWeapon().getType() == 4){
                        image = leftWithAxe;
                    }

                }
                break;

            case "right":
                if(isAttacking() == false){
                    image = right;
                }
                if(isAttacking()){
                    if(getCurrentWeapon().getType() == 3) {
                        image = rightWithSword;
                    }
                    else if(getCurrentWeapon().getType() == 4){
                        image = rightWithAxe;
                    }

                }
                break;

            case "down":
                if(isAttacking() == false){
                    image = front;
                }
                if(isAttacking()){
                    if(getCurrentWeapon().getType() == 3) {
                        image = frontWithSword;
                    }
                    else if(getCurrentWeapon().getType() == 4){
                        image = frontWithAxe;
                    }

                }
                break;
        }

        //Draws player invincible state
        if(isInvincible()){
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.3f)); //setting opacity level for player image
        }

        g2.drawImage(image, screenx, screeny, gp.playerSize, gp.playerSize, null);

        //Reset opacity
        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }


}
