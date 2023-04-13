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
     * Sets default values for an instance.
     */
    public void setDefault(){
        worldx = 100;
        worldy = 100;
        speed = 4;
    }


    /**
     * Gets images of an instance.
     */
    public void getEnemyImage(){

    }

    /**
     * Attacks a Player instance and getting healthPoints of a Player instance down.
     */
    public void attack(){
        
    }

    public int getHealthPoints() {
        return healthPoints;
    }

    public void setHealthPoints(int healthPoints) {
        this.healthPoints = healthPoints;
    }
}
