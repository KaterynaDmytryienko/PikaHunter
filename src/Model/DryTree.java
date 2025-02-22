package Model;

import View.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class DryTree extends InteractiveTile{
    public DryTree() throws IOException {
        back = ImageIO.read(getClass().getResourceAsStream("/tiles_interactive/drytree.png"));
        setDestructible(true);
    }

    /**Method checks if the player`s weapon is suitable for action or not.
     * @param entity
     * @return boolean
     */
    public boolean isSuitableWeapon(Entity entity){
        boolean isCorrectWeapon = false;

        if(entity.getCurrentWeapon().getType() == 4 ){
            isCorrectWeapon = true;
        }
        return isCorrectWeapon;
    }
}
