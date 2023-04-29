package Model;

import View.GamePannel;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Heart extends Item{
    public Heart(GamePannel gp){
        name = "heart";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objectsImages/heart_full.png"));
            image2 = ImageIO.read(getClass().getResourceAsStream("/objectsImages/heart_half.png"));
            image3 = ImageIO.read(getClass().getResourceAsStream("/objectsImages/heart_blank.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
