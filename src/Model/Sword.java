package Model;

import View.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Sword extends Entity{
    public Sword(GamePannel gp) throws IOException {
        super(gp);

        setName("Sword");
        back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/sword_normal.png"));
        setAttackValue(1);
        setDescription("[" + getName() + "]\nAn old sword." );
    }
}
