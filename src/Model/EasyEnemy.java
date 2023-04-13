package Model;

import View.GamePannel;

public class EasyEnemy extends Enemy{

    public EasyEnemy(GamePannel gameP) {
        super(gameP);
        setHealthPoints(2);
        setDefault();
    }

}
