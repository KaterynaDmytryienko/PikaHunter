package Model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

public class Key extends Item implements Serializable {
    public Key(){
        setCollision(true);
        setName("key");
        try{
            back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/key.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
