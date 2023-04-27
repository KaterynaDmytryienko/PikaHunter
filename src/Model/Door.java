package Model;

import javax.imageio.ImageIO;
import javax.swing.plaf.PanelUI;
import java.io.IOException;

public class Door extends Item{
    public Door() {
        name = "door";
        try {
            image = ImageIO.read(getClass().getResourceAsStream("/objectsImages/key.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
