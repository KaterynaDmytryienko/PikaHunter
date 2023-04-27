package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Item {
    public Key(){
        name = "key";
        try{
            image = ImageIO.read(getClass().getResourceAsStream("/objectsImages/key.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
