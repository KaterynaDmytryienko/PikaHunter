package data;

import Model.*;
import View.GamePannel;

import java.io.*;

public class SaveLoad {
    GamePannel gp;

    public SaveLoad(GamePannel gp) {
        this.gp = gp;
    }

    public Item getObject(String itemName) throws IOException {
        Item obj = null;

        switch (itemName) {
            case "axe":
                obj = new Axe();
                break;

            case "key":
                obj = new Key();
                break;
            case "elixir":
                obj = new Elixir();
                break;
            case "Shield":
                obj = new Shield(); break;
            case "Sword":
                obj = new Sword(); break;
        }
        return obj;
    }


    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
        DataStorage dataStorage = new DataStorage();

        //PLAYER STATUS
        dataStorage.maxLife = gp.player.getMaxLife();
        dataStorage.life = gp.player.getLife();
        dataStorage.dexterity = gp.player.getDexterity();
        dataStorage.strength = gp.player.getStrength();

        //PLAYER INVENTORY
        for(int i = 0; i < gp.player.inventory.size(); i++){
            dataStorage.itemNames.add(gp.player.inventory.get(i).getName());
            System.out.println(dataStorage.itemNames);
        }

        //PLAYER EQUIPMENT
        dataStorage.currentWeaponSlot = gp.player.getCurrentWeaponSlot();
        dataStorage.getCurrentShieldSlot = gp.player.getCurrentShieldSlot();

        //OBJECTS ON THE MAP

        oos.writeObject(dataStorage);


    }

    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

        //read the DataStorage object
        DataStorage ds = (DataStorage)ois.readObject();

        //PLAYER STATUS
        gp.player.setMaxLife(ds.maxLife);
        gp.player.setLife(ds.life);
        gp.player.setStrength(ds.strength);
        gp.player.setDexterity(ds.dexterity);



        //PLAYER INVENTORY
        gp.player.inventory.clear();
        for(int i = 0; i < ds.itemNames.size(); i++){
            gp.player.inventory.add(getObject(ds.itemNames.get(i)));
        }

        //PLAYER EQUIPMENT
        gp.player.setCurrentWeapon(gp.player.inventory.get(ds.currentWeaponSlot));
        gp.player.setCurrentShield(gp.player.inventory.get(ds.getCurrentShieldSlot));
        gp.player.getAttack();
        gp.player.getDefense();
        gp.player.getPlayerAttackImages();


    }
}
