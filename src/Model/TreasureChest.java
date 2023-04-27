package Model;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class TreasureChest extends Item {
    public TreasureChest(){
        name = "chest";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objectsImages/chest.png"));
            image2 =  ImageIO.read(getClass().getResourceAsStream("/objectsImages/chest_opened.png"));

        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
