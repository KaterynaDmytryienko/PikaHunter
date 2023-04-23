package Controller;

import Model.Entity;
import View.GamePannel;

public class CollisionController {
    GamePannel gp;

    public CollisionController(GamePannel gp){
        this.gp = gp;
    }

    public void checkTile (Entity entity){
        int entityLeftWorldX = entity.worldx + entity.solidArea.x;
        int entityRightWorldX = entity.worldx + entity.solidArea.x + entity.solidArea.width;
        int entityTopWorldY = entity.worldy + entity.solidArea.y;
        int entityBottomWorldY = entity.worldy + entity.solidArea.y + entity.solidArea.height;

        int entityLeftCol = entityLeftWorldX/gp.playerSize;
        int entityRightCol = entityRightWorldX/gp.playerSize;

        int entityTopRow = entityTopWorldY/gp.playerSize;

        int entityBottomRow = entityBottomWorldY/gp.playerSize;

        int tileNum1, tileNum2; // check two tiles near the player

        switch (entity.direction){
            case "up":
                entityTopRow = (entityTopWorldY - entity.speed)/gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];

                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/ gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].collision || gp.tileManager.tile[tileNum2].collision){
                    entity.collisionOn = true;
                }
                break;
        }

    }
}
