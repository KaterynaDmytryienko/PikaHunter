package data;

import Model.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    //PLAYER STATS
    private int maxLife;
    private int life;
    private int strength;
    private int dexterity;

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmount = new ArrayList<>();

    ArrayList<String> objectNames = new ArrayList<>();
    private int currentWeaponSlot;
    private int getCurrentShieldSlot;

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

    public int getCurrentWeaponSlot() {
        return currentWeaponSlot;
    }


    public int getGetCurrentShieldSlot() {
        return getCurrentShieldSlot;
    }
}
