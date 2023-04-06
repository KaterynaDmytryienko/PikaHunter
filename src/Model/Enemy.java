package Model;

import View.GamePannel;

public class Enemy extends Entity{
    GamePannel gameP;
    private int healthPoints;
    public Enemy(GamePannel gameP){
        this.gameP = gameP;
        setDefault();
    }

    /**
     * sets default values for an instance
     */
    public void setDefault(){
        x = 100;
        y = 100;
        speed = 4;
        this.healthPoints = 3;
    }


    /**
     * gets images of an instance
     */
    public void getEnemyImage(){

    }

    /**
     * attacks a Player instance and getting healthPoints of a Player instance down
     */
    public void attack(){
        
    }

}
