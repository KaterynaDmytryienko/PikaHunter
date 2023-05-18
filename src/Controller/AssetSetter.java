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
        gp.item[0].setWorldX(23 * gp.playerSize);
        gp.item[0].setWorldY(7 * gp.playerSize);

        gp.item[1] = new Key();
        gp.item[1].setWorldX(23 * gp.playerSize);
        gp.item[1].setWorldY(40 * gp.playerSize);

        gp.item[2] = new Key();
        gp.item[2].setWorldX(24 * gp.playerSize);
        gp.item[2].setWorldY(7 * gp.playerSize);

        gp.item[3] = new TreasureChest();
        gp.item[3].setWorldX(25 * gp.playerSize);
        gp.item[3].setWorldY(42 * gp.playerSize);
        gp.item[3].setCollision(true);

        gp.item[4] = new Axe();
        gp.item[4].setWorldX( 21 * gp.playerSize);
        gp.item[4].setWorldY(7 * gp.playerSize);

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

        gp.monster[2] = new Enemy(gp);
        gp.monster[2].worldx = 23 * gp.playerSize;
        gp.monster[2].worldy = 10 * gp.playerSize;
        gp.monster[2].collision =true;

        gp.monster[3] = new Enemy(gp);
        gp.monster[3].worldx = 23 * gp.playerSize;
        gp.monster[3].worldy = 40 * gp.playerSize;
        gp.monster[3].collision =true;
    }

    public void setInteractiveTiles() throws IOException {

        gp.iTile[18] = new DryTree();
        gp.iTile[18].setWorldX( 9 * gp.playerSize);
        gp.iTile[18].setWorldY( 37 * gp.playerSize);
        gp.iTile[18].setCollision(true);

        gp.iTile[17] = new DryTree();
        gp.iTile[17].setWorldX( 9 * gp.playerSize);
        gp.iTile[17].setWorldY(36 * gp.playerSize);
        gp.iTile[17].setCollision(true);

        gp.iTile[16] = new DryTree();
        gp.iTile[16].setWorldX(10 * gp.playerSize);
        gp.iTile[16].setWorldY(36 * gp.playerSize);
        gp.iTile[16].setCollision(true);

        gp.iTile[15] = new DryTree();
        gp.iTile[15].setWorldX( 10 * gp.playerSize);
        gp.iTile[15].setWorldY(37 * gp.playerSize );
        gp.iTile[15].setCollision(true);


        gp.iTile[13] = new DryTree();
        gp.iTile[13].setWorldX( 11 * gp.playerSize);
        gp.iTile[13].setWorldY(37 * gp.playerSize );
        gp.iTile[13].setCollision(true);

        gp.iTile[14] = new DryTree();
        gp.iTile[14].setWorldX( 11 * gp.playerSize);
        gp.iTile[14].setWorldY(36 * gp.playerSize);
        gp.iTile[14].setCollision(true);

        gp.iTile[12] = new DryTree();
        gp.iTile[12].setWorldX( 34 * gp.playerSize);
        gp.iTile[12].setWorldY(7 * gp.playerSize);
        gp.iTile[12].setCollision(true);

        gp.iTile[11] = new DryTree();
        gp.iTile[11].setWorldX(33 * gp.playerSize);
        gp.iTile[11].setWorldY( 7 * gp.playerSize);
        gp.iTile[11].setCollision(true);

        gp.iTile[10] = new DryTree();
        gp.iTile[10].setWorldX(33 * gp.playerSize);
        gp.iTile[10].setWorldY(6 * gp.playerSize);
        gp.iTile[10].setCollision(true);

        gp.iTile[9] = new DryTree();
        gp.iTile[9].setWorldX(34 * gp.playerSize);
        gp.iTile[9].setWorldY( 6 * gp.playerSize);
        gp.iTile[9].setCollision(true);


        gp.iTile[8] = new DryTree();
        gp.iTile[8].setWorldX(35 * gp.playerSize);
        gp.iTile[8].setWorldY( 6 * gp.playerSize);
        gp.iTile[8].setCollision(true);

        gp.iTile[0] = new DryTree();
        gp.iTile[0].setWorldX(35 * gp.playerSize);
        gp.iTile[0].setWorldY(7 * gp.playerSize);
        gp.iTile[0].setCollision(true);


        gp.iTile[1] = new DryTree();
        gp.iTile[1].setWorldX(36 * gp.playerSize);
        gp.iTile[1].setWorldY( 7 * gp.playerSize);
        gp.iTile[1].setCollision(true);

        gp.iTile[2] = new DryTree();
        gp.iTile[2].setWorldX( 37 * gp.playerSize);
        gp.iTile[2].setWorldY (7 * gp.playerSize);
        gp.iTile[2].setCollision(true);

        gp.iTile[3] = new DryTree();
        gp.iTile[3].setWorldX( 38 * gp.playerSize);
        gp.iTile[3].setWorldY(7 * gp.playerSize);
        gp.iTile[3].setCollision(true);

        gp.iTile[4] = new DryTree();
        gp.iTile[4].setWorldX(39 * gp.playerSize);
        gp.iTile[4].setWorldY(7 * gp.playerSize );
        gp.iTile[4].setCollision(true);

        gp.iTile[5] = new DryTree();
        gp.iTile[5].setWorldX(40 * gp.playerSize);
        gp.iTile[5].setWorldY( 7 * gp.playerSize );
        gp.iTile[5].setCollision(true);

        gp.iTile[6] = new DryTree();
        gp.iTile[6].setWorldX(41 * gp.playerSize);
        gp.iTile[6].setWorldY(7 * gp.playerSize);
        gp.iTile[6].setCollision(true);

        gp.iTile[7] = new DryTree();
        gp.iTile[7].setWorldX(42 * gp.playerSize);
        gp.iTile[7].setWorldY(7 * gp.playerSize);
        gp.iTile[7].setCollision(true);
    }
}
