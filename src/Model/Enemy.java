package Model;

import View.GamePannel;


import javax.imageio.ImageIO;
import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class Enemy extends Entity{
    public Enemy (GamePannel gp) throws IOException {
        super(gp);
        direction = "up";
        speed = 1;
        setMaxLife(4);
        setLife(getMaxLife());

        solidArea.x = 3;
        solidArea.y = 15;
        solidArea.width = 20;
        solidArea.height = 25;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setName("Slime");
        getImage();
    }

    /**
     * Method getts image of an enemy.
     */
    public void getImage() {
        try {
            front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monsters/greenslime_down_1.png")));
            back = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monsters/greenslime_down_2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Method setts the way enemy moves on a map.
     */
    //SETTING SLIME BEHAVIOUR
    public void setAction(){
        actionLockCounter ++;

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1;
            if(i <= 25){
                direction = "up";
            }

            if(i>25 && i <= 50){
                direction = "down";

            }

            if(i > 50 && i <= 75){
                direction = "left";
            }

            if(i > 75 && i <= 100){
                direction = "right";
            }
            actionLockCounter = 0;
        }

    }
}
