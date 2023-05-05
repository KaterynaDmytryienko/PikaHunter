package Model;

import View.GamePannel;


import javax.imageio.ImageIO;
import java.awt.*;
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
        solidArea.y = 18;
        solidArea.width = 20;
        solidArea.height = 25;

        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setName("Slime");
        getImage();
    }

    public void getImage() {
        try {
            front = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monsters/greenslime_down_1.png")));
            back = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream("/monsters/greenslime_down_2.png")));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    //SETTING SLIME BEHAVIOUR
    public void setAction(){
        System.out.println("setting action!");
        actionLockCounter ++;
        System.out.println(actionLockCounter);

        if(actionLockCounter == 120){

            Random random = new Random();
            int i = random.nextInt(100)+1;
            System.out.println(i);
            if(i <= 25){
                direction = "up";
                System.out.println("direction is up!");
            }

            if(i>25 && i <= 50){
                direction = "down";
                System.out.println("direction is down!");
            }

            if(i > 50 && i <= 75){
                direction = "left";
                System.out.println("direction is left!");
            }

            if(i > 75 && i <= 100){
                direction = "right";
                System.out.println("direction is right!");
            }

            actionLockCounter = 0;
            gp.collisionController.checkTile(this);
            gp.collisionController.checkObject(this, false);
//            gp.collisionController.checkEntity(this, gp.monster);
        }
    }
}
