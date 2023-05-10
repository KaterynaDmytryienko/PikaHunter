package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Elixir extends Item {
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
