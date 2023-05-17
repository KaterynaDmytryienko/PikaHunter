package data;

import Model.Item;

import java.io.Serializable;
import java.util.ArrayList;

public class DataStorage implements Serializable {
    //PLAYER STATS
    int maxLife;
    int life;
    int strength;
    int dexterity;

    ArrayList<String> itemNames = new ArrayList<>();
    ArrayList<Integer> itemAmount = new ArrayList<>();

    ArrayList<String> objectNames = new ArrayList<>();
    int currentWeaponSlot;
    int getCurrentShieldSlot;

}
