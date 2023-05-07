package Controller;

import Model.*;
import View.GamePannel;

import java.io.IOException;

public class AssetSetter {
    GamePannel gp;

    public AssetSetter(GamePannel gp){
        this.gp = gp;
    }

    public void setObject(){
        gp.item[0] = new Key(); //because Object_key is a subclass of ObjectSuper
        gp.item[0].worldX = 23 * gp.playerSize;
        gp.item[0].worldY = 7 * gp.playerSize;

        gp.item[1] = new Key();
        gp.item[1].worldX = 23 * gp.playerSize;
        gp.item[1].worldY = 40 * gp.playerSize;

        gp.item[2] = new Key();
        gp.item[2].worldX = 24 * gp.playerSize;
        gp.item[2].worldY = 7 * gp.playerSize;

        gp.item[3] = new TreasureChest();
        gp.item[3].worldX = 25 * gp.playerSize;
        gp.item[3].worldY = 42 * gp.playerSize;
        gp.item[3].collision = true;

    }

    public void setMonster() throws IOException {
        gp.monster[0] = new Enemy(gp);
        gp.monster[0].worldX = 21 * gp.playerSize;
        gp.monster[0].worldY = 21 * gp.playerSize;
        gp.monster[0].collision =true;

        gp.monster[1] = new Enemy(gp);
        gp.monster[1].worldX = 23 * gp.playerSize;
        gp.monster[1].worldY = 23 * gp.playerSize;
        gp.monster[1].collision =true;
    }
}
