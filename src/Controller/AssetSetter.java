package Controller;

import Model.*;
import View.GamePannel;

import java.io.IOException;

public class AssetSetter {
    GamePannel gp;

    public AssetSetter(GamePannel gp){
        this.gp = gp;
    }

    public void setObject() throws IOException {
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

        gp.item[4] = new Axe();
        gp.item[4].worldX = 21 * gp.playerSize;
        gp.item[4].worldY = 7 * gp.playerSize;

    }

    public void setMonster() throws IOException {
        gp.monster[0] = new Enemy(gp);
        gp.monster[0].worldx = 21 * gp.playerSize;
        gp.monster[0].worldy = 21 * gp.playerSize;
        gp.monster[0].collision =true;

        gp.monster[1] = new Enemy(gp);
        gp.monster[1].worldx = 23 * gp.playerSize;
        gp.monster[1].worldy = 23 * gp.playerSize;
        gp.monster[1].collision =true;
    }

    public void setInteractiveTiles() throws IOException {

        gp.iTile[12] = new DryTree();
        gp.iTile[12].worldX = 34 * gp.playerSize; //42
        gp.iTile[12].worldY = 7 * gp.playerSize ;
        gp.iTile[12].collision = true;

        gp.iTile[11] = new DryTree();
        gp.iTile[11].worldX = 33 * gp.playerSize; //42
        gp.iTile[11].worldY = 7 * gp.playerSize ;
        gp.iTile[11].collision = true;

        gp.iTile[10] = new DryTree();
        gp.iTile[10].worldX = 33 * gp.playerSize; //42
        gp.iTile[10].worldY = 6 * gp.playerSize;
        gp.iTile[10].collision = true;

        gp.iTile[9] = new DryTree();
        gp.iTile[9].worldX = 34 * gp.playerSize; //42
        gp.iTile[9].worldY = 6 * gp.playerSize ;


        gp.iTile[8] = new DryTree();
        gp.iTile[8].worldX = 35 * gp.playerSize; //42
        gp.iTile[8].worldY = 6 * gp.playerSize ;

        gp.iTile[0] = new DryTree();
        gp.iTile[0].worldX = 35 * gp.playerSize; //42
        gp.iTile[0].worldY = 7 * gp.playerSize ;


        gp.iTile[1] = new DryTree();
        gp.iTile[1].worldX = 36 * gp.playerSize; //42
        gp.iTile[1].worldY = 7 * gp.playerSize ;

        gp.iTile[2] = new DryTree();
        gp.iTile[2].worldX = 37 * gp.playerSize; //42
        gp.iTile[2].worldY = 7 * gp.playerSize ;

        gp.iTile[3] = new DryTree();
        gp.iTile[3].worldX = 38 * gp.playerSize; //42
        gp.iTile[3].worldY = 7 * gp.playerSize ;

        gp.iTile[4] = new DryTree();
        gp.iTile[4].worldX = 39 * gp.playerSize; //42
        gp.iTile[4].worldY = 7 * gp.playerSize ;

        gp.iTile[5] = new DryTree();
        gp.iTile[5].worldX = 40 * gp.playerSize; //42
        gp.iTile[5].worldY = 7 * gp.playerSize ;

        gp.iTile[6] = new DryTree();
        gp.iTile[6].worldX = 41 * gp.playerSize; //42
        gp.iTile[6].worldY = 7 * gp.playerSize ;

        gp.iTile[7] = new DryTree();
        gp.iTile[7].worldX = 42 * gp.playerSize; //42
        gp.iTile[7].worldY = 7 * gp.playerSize ;
    }
}
