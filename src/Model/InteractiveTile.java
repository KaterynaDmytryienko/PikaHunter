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

    public boolean isSuitableWeapon(Entity entity){
        boolean isCorrectWeapon = false;
        return isCorrectWeapon;
    }

    public InteractiveTile getDestroyedForm() throws IOException {
        InteractiveTile tile = null;
        return tile;
    }
    public void update(){}


}
