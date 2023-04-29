package Controller;

import Model.Door;
import Model.Elixir;
import Model.TreasureChest;
import Model.Key;
import View.GamePannel;

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


}
