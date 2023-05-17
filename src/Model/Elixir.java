package Model;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.Serializable;

public class Elixir extends Item implements Serializable {
    public Elixir(){
        setName("elixir");
        try{
            back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/elixir.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
