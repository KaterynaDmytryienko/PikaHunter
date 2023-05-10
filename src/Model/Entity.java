package Model;

import View.GamePannel;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Entity {
    public int spriteCounter;
    public boolean collision;  //ABSTRACT CLASS
    GamePannel gp;
    public int speed;
    private boolean attacking = false;
    public BufferedImage image;
    public BufferedImage front;
    public BufferedImage right;
    public BufferedImage left;
    public BufferedImage back;
    public String direction;
    public BufferedImage backWithSword, frontWithSword, leftWithSword, rightWithSword;
    public BufferedImage backWithAxe, frontWithAxe, leftWithAxe, rightWithAxe;
    public Rectangle solidArea = new Rectangle(); // class for abstract rectangle
    public Rectangle attackArea = new Rectangle(0, 0, 0, 0); //entity attack area
    public int worldX;
    public int worldY;

    public int worldx, worldy;

    public int solidAreaDefaultX = 0, solidAreaDefaultY = 0;
    public boolean collisionOn = false;

    private boolean invincible = false;

    public int invincibleCounter;
    private boolean hpBarOn = false;

    //CHARACTER STATUS
    private int maxLife;
    private int life;
    private String name;
    public int actionLockCounter = 0;

    public int hpBarCounter = 0;
    private int level;
    private int strength;
    private int dexterity;
    private int attack;
    private int defense;
    private int exp;
    private Item currentWeapon;
    private Item currentShield;
    private String description = "";

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    //ITEM ATTRIBUTES
    private int attackValue;
    private int defenceValue;
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getDexterity() {
        return dexterity;
    }

    public void setDexterity(int dexterity) {
        this.dexterity = dexterity;
    }

    public int getAttack() {
        return attack;
    }

    public int setAttack(int attack) {
        this.attack = attack;
        return attack;
    }

    public int getDefense() {
        return defense;
    }

    public int setDefense(int defense) {
        this.defense = defense;
        return defense;
    }

    public int getExp() {
        return exp;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public Item getCurrentWeapon() {
        return currentWeapon;
    }

    public void setCurrentWeapon(Item currentWeapon) {
        this.currentWeapon = currentWeapon;
    }

    public Item getCurrentShield() {
        return currentShield;
    }

    public void setCurrentShield(Item currentShield) {
        this.currentShield = currentShield;
    }

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

    public boolean isInvincible() {
        return invincible;
    }

    public void setInvincible(boolean invincible) {
        this.invincible = invincible;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void setAttacking(boolean attacking) {
        this.attacking = attacking;
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

    public boolean isHpBarOn() {
        return hpBarOn;
    }

    public void setHpBarOn(boolean hpBarOn) {
        this.hpBarOn = hpBarOn;
    }

    public void update(){
        collisionOn = false;
        setAction();
        if (!collisionOn){

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


            if(isInvincible()){
                invincibleCounter++;
                if(invincibleCounter > 40){
                    setInvincible(false);
                    invincibleCounter = 0;
                }
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

            //MONSTER HEALTH BAR
            if(isHpBarOn()) {
                double oneScale = (double) gp.playerSize / getMaxLife(); // for changing health bar
                double hpBarValue = oneScale * getLife();


                g2.setColor(new Color(35, 35, 35));
                g2.fillRect(screenX - 1, screenY - 16, gp.playerSize + 2, 12);
                g2.setColor(new Color(255, 0, 30)); //red colour
                g2.fillRect(screenX, screenY - 15, (int) hpBarValue, 10); //setting health bar slightly above the entity
                hpBarCounter ++;

                if(hpBarCounter > 600){
                    hpBarCounter = 0;
                    setHpBarOn(false);
                }
            }

            if(isInvincible()){
                setHpBarOn(true);
                hpBarCounter = 0;
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.4f)); //setting opacity level for player image
            }
            g2.drawImage(image, screenX, screenY, gp.playerSize, gp.playerSize, null);
            //Reset opacity
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

        }
    }

}
