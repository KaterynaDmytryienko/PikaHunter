package Model;

import View.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sword extends Item{
    public Sword() throws IOException {
        setName("Sword");
        back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/sword_normal.png"));
        setAttackValue(1);
        setDescription("[" + getName() + "]\nAn old sword." );
        attackArea.width = 36;
        attackArea.height = 36;
        setType(3);
    }
}
