package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class TreasureChest extends Item {
    public TreasureChest(){
        setName("chest");
        try{
            back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/chest.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
