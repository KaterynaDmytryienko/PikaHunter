package Model;

import javax.imageio.ImageIO;
import java.io.IOException;

public class Axe extends Item{
  public Axe() throws IOException {
      setName("axe");
      back = ImageIO.read(getClass().getResourceAsStream("/objectsImages/axe.png"));
      setAttackValue(2); //stronger that a sword
      setDescription("An old axe");
      attackArea.width = 30;
      attackArea.height = 30;
      setType(4);

  }
}
