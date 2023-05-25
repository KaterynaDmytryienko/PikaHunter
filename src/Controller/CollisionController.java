package Controller;

import Model.Entity;
import Model.Item;
import View.GamePannel;

public class CollisionController {
    GamePannel gp;

    public CollisionController(GamePannel gp){
        this.gp = gp;
    }

    /**
     * Method checks collision with a tile on a map.
     * @param entity
     */
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

                if(gp.tileManager.tile[tileNum1].isCollision() || gp.tileManager.tile[tileNum2].isCollision()){
                    entity.setCollisionOn(true);
                }
                break;
            case "down":
                entityBottomRow = (entityBottomWorldY + entity.speed)/ gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].isCollision() || gp.tileManager.tile[tileNum2].isCollision()){
                    entity.setCollisionOn(true);
                }
                break;
            case "left":
                entityLeftCol = (entityLeftWorldX - entity.speed)/ gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityLeftCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityLeftCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].isCollision() || gp.tileManager.tile[tileNum2].isCollision()){
                    entity.setCollisionOn(true);
                }
                break;
            case "right":
                entityRightCol = (entityRightWorldX + entity.speed)/ gp.playerSize;
                tileNum1 = gp.tileManager.mapTileNum[entityRightCol][entityTopRow];
                tileNum2 = gp.tileManager.mapTileNum[entityRightCol][entityBottomRow];
                if(gp.tileManager.tile[tileNum1].isCollision() || gp.tileManager.tile[tileNum2].isCollision()){
                    entity.setCollisionOn(true);
                }
                break;
        }

    }

    /**
     * Method checks collision with objects on a map.
     * @param entity
     * @param isPlayer
     * @return int
     */
    public int checkObject(Entity entity, boolean isPlayer){
        int index = 999;

        for(int i = 0; i < gp.item.length; i++){
            if(gp.item[i] != null){

                //Get entity solid area position
                  entity.solidArea.x = entity.worldx + entity.solidArea.x;
                  entity.solidArea.y = entity.worldy + entity.solidArea.y;


                //Get the object solid area position
                gp.item[i].solidArea.x = gp.item[i].getWorldX() + gp.item[i].solidArea.x;
                gp.item[i].solidArea.y = gp.item[i].getWorldY() + gp.item[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;

                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }

                if(entity.solidArea.intersects(gp.item[i].solidArea)){ //checks if two tiles are touching or not
                    if(gp.item[i].isCollision()){
                        entity.setCollisionOn(true);
                    }

                    if(isPlayer){
                        index = i;
                    }

                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.item[i].solidArea.x = gp.item[i].getSolidAreaDefaultX();
                gp.item[i].solidArea.y = gp.item[i].getSolidAreaDefaultY();
            }

        }
        return index;
    }


    /**
     * Method checks collision with entity (player collision to a monster).
     * @param entity
     * @param target
     * @return int
     */
    public int checkEntity(Entity entity, Entity[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++){
            if(target[i] != null){

                //Get entity solid area position
                entity.solidArea.x = entity.worldx + entity.solidArea.x;
                entity.solidArea.y = entity.worldy + entity.solidArea.y;


                //Get the object solid area position
                target[i].solidArea.x = target[i].worldx + target[i].solidArea.x;
                target[i].solidArea.y = target[i].worldy + target[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;

                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                    if(target[i] != entity){
                        entity.setCollisionOn(true);
                        index = i;
                    }
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].solidAreaDefaultX;
                target[i].solidArea.y = target[i].solidAreaDefaultY;
            }

        }
        return index;

    }

    /**
     * Method checks collision with a player (monster collision to a payer)
     * @param entity
     * @return boolean
     */
    public boolean checkPlayer(Entity entity){
        boolean contactPlayer = false;
        //Get entity solid area position
        entity.solidArea.x = entity.worldx + entity.solidArea.x;
        entity.solidArea.y = entity.worldy + entity.solidArea.y;



        //Get the object solid area position
        gp.player.solidArea.x =gp.player.worldx + gp.player.solidArea.x;
        gp.player.solidArea.y = gp.player.worldy +gp.player.solidArea.y;


        switch (entity.direction){
            case "up":
                entity.solidArea.y -= entity.speed;
                break;

            case "down":
                entity.solidArea.y += entity.speed;
                break;

            case "left":
                entity.solidArea.x -= entity.speed;
                break;

            case"right":
                entity.solidArea.x += entity.speed;
                break;
        }


        if(entity.solidArea.intersects(gp.player.solidArea)){
            entity.setCollisionOn(true);
            contactPlayer = true;
        }
            entity.solidArea.x = entity.solidAreaDefaultX;
            entity.solidArea.y = entity.solidAreaDefaultY;
            gp.player.solidArea.x = gp.player.solidAreaDefaultX;
            gp.player.solidArea.y = gp.player.solidAreaDefaultY;
            return contactPlayer;

    }


    /**
     * Method checks collision with items on a map.
     * @param entity
     * @param target
     * @return int
     */
    public int checkItem(Entity entity, Item[] target){
        int index = 999;

        for(int i = 0; i < target.length; i++){
            if(target[i] != null){

                //Get entity solid area position
                entity.solidArea.x = entity.worldx + entity.solidArea.x;
                entity.solidArea.y = entity.worldy + entity.solidArea.y;


                //Get the object solid area position
                target[i].solidArea.x = target[i].getWorldX() + target[i].solidArea.x;
                target[i].solidArea.y = target[i].getWorldY() + target[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        break;

                    case "down":
                        entity.solidArea.y += entity.speed;
                        break;

                    case "left":
                        entity.solidArea.x -= entity.speed;
                        break;

                    case"right":
                        entity.solidArea.x += entity.speed;
                        break;
                }
                if(entity.solidArea.intersects(target[i].solidArea)){
                        entity.setCollisionOn(true);
                        index = i;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                target[i].solidArea.x = target[i].getSolidAreaDefaultX();
                target[i].solidArea.y = target[i].getSolidAreaDefaultY();
            }

        }
        return index;

    }
}
