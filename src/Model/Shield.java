package Model;

import View.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Shield extends Entity{
    public Shield(GamePannel gp) throws IOException {
        super(gp);
        setName("Shield");
        back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/shield_wood.png"));
        setDefenceValue(1);
    }
}
