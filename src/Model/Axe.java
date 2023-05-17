package Model;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.io.Serializable;

public class Axe extends Item implements Serializable {

  public Axe() throws IOException {
      collision = true;
      setName("axe");
      back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/axe.png"));
      setAttackValue(2); //stronger that a sword
      setDescription("An old axe");
      attackArea.width = 30;
      attackArea.height = 30;
      setType(4);

  }
}
