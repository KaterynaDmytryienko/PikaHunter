package Controller;

import Model.*;
import View.GamePannel;

import java.io.IOException;

public class AssetSetter {
    GamePannel gp;

    public AssetSetter(GamePannel gp){
        this.gp = gp;
    }

    /**
     * Method setts objects on a map with defined position.
     * @throws IOException
     */
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

    /**
     * Method sets monsters on the map with defined position.
     * @throws IOException
     */
    public void setMonster() throws IOException {
        gp.getMonster()[0] = new Enemy(gp);
        gp.getMonster()[0].worldx = 21 * gp.playerSize;
        gp.getMonster()[0].worldy = 21 * gp.playerSize;
        gp.getMonster()[0].collision =true;

        gp.getMonster()[1] = new Enemy(gp);
        gp.getMonster()[1].worldx = 23 * gp.playerSize;
        gp.getMonster()[1].worldy = 23 * gp.playerSize;
        gp.getMonster()[1].collision =true;

        gp.getMonster()[2] = new Enemy(gp);
        gp.getMonster()[2].worldx = 23 * gp.playerSize;
        gp.getMonster()[2].worldy = 10 * gp.playerSize;
        gp.getMonster()[2].collision =true;

        gp.getMonster()[3] = new Enemy(gp);
        gp.getMonster()[3].worldx = 23 * gp.playerSize;
        gp.getMonster()[3].worldy = 40 * gp.playerSize;
        gp.getMonster()[3].collision =true;
    }

    /**
     * Method sets interactive tiles on the map with defined position.
     * @throws IOException
     */
    public void setInteractiveTiles() throws IOException {

        gp.getiTile()[18] = new DryTree();
        gp.getiTile()[18].setWorldX( 9 * gp.playerSize);
        gp.getiTile()[18].setWorldY( 37 * gp.playerSize);
        gp.getiTile()[18].setCollision(true);

        gp.getiTile()[17] = new DryTree();
        gp.getiTile()[17].setWorldX( 9 * gp.playerSize);
        gp.getiTile()[17].setWorldY(36 * gp.playerSize);
        gp.getiTile()[17].setCollision(true);

        gp.getiTile()[16] = new DryTree();
        gp.getiTile()[16].setWorldX(10 * gp.playerSize);
        gp.getiTile()[16].setWorldY(36 * gp.playerSize);
        gp.getiTile()[16].setCollision(true);

        gp.getiTile()[15] = new DryTree();
        gp.getiTile()[15].setWorldX( 10 * gp.playerSize);
        gp.getiTile()[15].setWorldY(37 * gp.playerSize );
        gp.getiTile()[15].setCollision(true);


        gp.getiTile()[13] = new DryTree();
        gp.getiTile()[13].setWorldX( 11 * gp.playerSize);
        gp.getiTile()[13].setWorldY(37 * gp.playerSize );
        gp.getiTile()[13].setCollision(true);

        gp.getiTile()[14] = new DryTree();
        gp.getiTile()[14].setWorldX( 11 * gp.playerSize);
        gp.getiTile()[14].setWorldY(36 * gp.playerSize);
        gp.getiTile()[14].setCollision(true);

        gp.getiTile()[12] = new DryTree();
        gp.getiTile()[12].setWorldX( 34 * gp.playerSize);
        gp.getiTile()[12].setWorldY(7 * gp.playerSize);
        gp.getiTile()[12].setCollision(true);

        gp.getiTile()[11] = new DryTree();
        gp.getiTile()[11].setWorldX(33 * gp.playerSize);
        gp.getiTile()[11].setWorldY( 7 * gp.playerSize);
        gp.getiTile()[11].setCollision(true);

        gp.getiTile()[10] = new DryTree();
        gp.getiTile()[10].setWorldX(33 * gp.playerSize);
        gp.getiTile()[10].setWorldY(6 * gp.playerSize);
        gp.getiTile()[10].setCollision(true);

        gp.getiTile()[9] = new DryTree();
        gp.getiTile()[9].setWorldX(34 * gp.playerSize);
        gp.getiTile()[9].setWorldY( 6 * gp.playerSize);
        gp.getiTile()[9].setCollision(true);


        gp.getiTile()[8] = new DryTree();
        gp.getiTile()[8].setWorldX(35 * gp.playerSize);
        gp.getiTile()[8].setWorldY( 6 * gp.playerSize);
        gp.getiTile()[8].setCollision(true);

        gp.getiTile()[0] = new DryTree();
        gp.getiTile()[0].setWorldX(35 * gp.playerSize);
        gp.getiTile()[0].setWorldY(7 * gp.playerSize);
        gp.getiTile()[0].setCollision(true);


        gp.getiTile()[1] = new DryTree();
        gp.getiTile()[1].setWorldX(36 * gp.playerSize);
        gp.getiTile()[1].setWorldY( 7 * gp.playerSize);
        gp.getiTile()[1].setCollision(true);

        gp.getiTile()[2] = new DryTree();
        gp.getiTile()[2].setWorldX( 37 * gp.playerSize);
        gp.getiTile()[2].setWorldY (7 * gp.playerSize);
        gp.getiTile()[2].setCollision(true);

        gp.getiTile()[3] = new DryTree();
        gp.getiTile()[3].setWorldX( 38 * gp.playerSize);
        gp.getiTile()[3].setWorldY(7 * gp.playerSize);
        gp.getiTile()[3].setCollision(true);

        gp.getiTile()[4] = new DryTree();
        gp.getiTile()[4].setWorldX(39 * gp.playerSize);
        gp.getiTile()[4].setWorldY(7 * gp.playerSize );
        gp.getiTile()[4].setCollision(true);

        gp.getiTile()[5] = new DryTree();
        gp.getiTile()[5].setWorldX(40 * gp.playerSize);
        gp.getiTile()[5].setWorldY( 7 * gp.playerSize );
        gp.getiTile()[5].setCollision(true);

        gp.getiTile()[6] = new DryTree();
        gp.getiTile()[6].setWorldX(41 * gp.playerSize);
        gp.getiTile()[6].setWorldY(7 * gp.playerSize);
        gp.getiTile()[6].setCollision(true);

        gp.getiTile()[7] = new DryTree();
        gp.getiTile()[7].setWorldX(42 * gp.playerSize);
        gp.getiTile()[7].setWorldY(7 * gp.playerSize);
        gp.getiTile()[7].setCollision(true);
    }
}
