package Controller;

import View.GamePannel;

import java.awt.*;
import java.io.IOException;
import java.util.logging.Logger;

public class EventHandler {
    GamePannel gp;
    public  Rectangle eventRect;
    public int eventRectDefaultX;
   public int eventRectDefaultY;
    private static final Logger logger = Logger.getLogger(EventHandler.class.getName());


    public EventHandler(GamePannel gp){
        this.gp = gp;

        eventRect = new Rectangle();
        eventRect.x = 23;
        eventRect.y = 23;
        eventRect.width = 2;
        eventRect.height = 2;
        eventRectDefaultX = eventRect.x;
        eventRectDefaultY = eventRect.y;
    }


    /**
     * Method checks events occurring during the game.
     * @throws IOException
     */
    public void checkEvent() throws IOException {
        if(hit(11, 11, "back")){
            savingPool();
            logger.info("Game is saved.");

        }

    }

    /**
     * Method checks if player hit a certain place on a map.
     * @param eventCol
     * @param eventRow
     * @param requiredDirection
     * @return boolean
     */
    public boolean hit(int eventCol, int eventRow, String requiredDirection){

        boolean hit = false;
        gp.player.solidArea.x = gp.player.worldx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldy + gp.player.solidArea.y;
        eventRect.x = eventCol *gp.playerSize + eventRect.x;
        eventRect.y = eventRow *gp.playerSize + eventRect.y;


        if(gp.player.solidArea.intersects(eventRect)){

                hit = true;
        }
        gp.player.solidArea.x = gp.player.solidAreaDefaultX;
        gp.player.solidArea.y = gp.player.solidAreaDefaultY;
        eventRect.x = eventRectDefaultX;
        eventRect.y = eventRectDefaultY;

        return hit;

    }


    /**
     * Method allows player to save the game near the water pool.
     * @throws IOException
     */
    public void savingPool() throws IOException {
            gp.getSaveLoad().save();
            gp.gameState = gp.getDialogueState();
    }

}
