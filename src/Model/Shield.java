package Model;

import View.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

public class Shield extends Item implements Serializable {
    public Shield() throws IOException {
        setName("Shield");
        back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/shield_wood.png"));
        setDefenceValue(1);
        setDescription("[" + getName() + "]\nAn old shield,\n created for defence." );
        setType(5);
    }
}
