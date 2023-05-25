package Model;

import View.GamePannel;

import java.io.IOException;

public class InteractiveTile extends Item{
    private boolean destructible = false;

    public boolean isDestructible() {
        return destructible;
    }

    public void setDestructible(boolean destructible) {
        this.destructible = destructible;
    }

    /**
     * Method checks if weapon is suitable to interact with interactive tile.
     * @param entity
     * @return boolean
     */
    public boolean isSuitableWeapon(Entity entity){
        boolean isCorrectWeapon = false;
        return isCorrectWeapon;
    }

    public void update(){}

}
