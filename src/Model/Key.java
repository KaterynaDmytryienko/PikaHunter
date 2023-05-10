package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Key extends Item {
    public Key(){
        setName("key");
        try{
            back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/key.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
