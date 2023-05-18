package Controller;

import View.GamePannel;

import java.awt.*;
import java.io.IOException;

public class EventHandler {
    GamePannel gp;
    Rectangle eventRect;
    int eventRectDefaultX;
    int eventRectDefaultY;


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


    public void checkEvent() throws IOException {
//        if(hit(10, 11, "right")){
//            damagePit();
//        }
        if(hit(11, 11, "back")){
            savingPool();
        }
        if(gp.getKeyHandler().isPressedEnter()){
            healing();
        }

    }

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

    public void damagePit(){
        gp.player.setLife(gp.player.getLife()-1);
    }

    public void healing(){
        if(gp.getKeyHandler().isPressedEnter() && gp.player.getElixirAmount() > 0){
            gp.player.setLife(gp.player.getMaxLife());
        }
        gp.getKeyHandler().setPressedEnter(false);
    }

    public void savingPool() throws IOException {
            gp.saveLoad.save();
            gp.gameState = gp.dialogueState;
    }

}
