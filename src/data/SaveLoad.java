package data;

import Model.*;
import View.GamePannel;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Logger;

public class SaveLoad {
    private static final Logger logger = Logger.getLogger(SaveLoad.class.getName());
    GamePannel gp;

    public SaveLoad(GamePannel gp) {
        this.gp = gp;
    }

    /**
     * Method gets an object depending on object name.
//     * @param itemName
     * @return Item
     * @throws IOException
     */
    public Item getObject(Item.Objects objectEnum) throws IOException {
        Item obj = null;

        if (objectEnum == Item.Objects.AXE) {
            obj = new Axe();
        } else if (objectEnum == Item.Objects.KEY) {
            obj = new Key();
        } else if (objectEnum == Item.Objects.ELIXIR) {
            obj = new Elixir();
        } else if (objectEnum == Item.Objects.SHIELD) {
            obj = new Shield();
        } else if (objectEnum == Item.Objects.SWORD) {
            obj = new Sword();
        }

        return obj;
    }



    /**
     * Method saves player`s states and his current inventory.
     * @throws IOException
     */
    public void save() throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("save.dat"));
        DataStorage dataStorage = new DataStorage();

        //PLAYER STATUS
        dataStorage.setMaxLife( gp.player.getMaxLife());
        dataStorage.setLife(gp.player.getLife());
        dataStorage.setDexterity(gp.player.getDexterity());
        dataStorage.setStrength(gp.player.getStrength());

        //PLAYER INVENTORY
        for(int i = 0; i < gp.player.inventory.size(); i++){
            dataStorage.itemNames.add(gp.player.inventory.get(i).getName());
        }

        oos.writeObject(dataStorage);
        logger.info("Game is saved.");

    }


    /**
     * Method loads saved player`s states and inventory.
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void load() throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("save.dat"));

        //read the DataStorage object
        DataStorage ds = (DataStorage) ois.readObject();

        //PLAYER STATUS
        gp.player.setMaxLife(ds.getMaxLife());
        gp.player.setLife(ds.getLife());
        gp.player.setStrength(ds.getStrength());
        gp.player.setDexterity(ds.getDexterity());


        //PLAYER INVENTORY
        gp.player.inventory.clear();
        for (String itemName : ds.itemNames) {
            Item.Objects objectEnum = Item.Objects.valueOf(itemName.toUpperCase());
            Item item = getObject(objectEnum);
            gp.player.inventory.add(item);
        }

        //PLAYER EQUIPMENT
        gp.player.setCurrentWeapon(gp.player.inventory.get(ds.getCurrentWeaponSlot()));
        gp.player.setCurrentShield(gp.player.inventory.get(ds.getGetCurrentShieldSlot()));
        gp.player.getAttack();
        gp.player.getDefense();
        gp.player.getPlayerAttackImages();
        logger.info("Game is loaded.");
    }

    }


