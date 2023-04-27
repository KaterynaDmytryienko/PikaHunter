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

    public int checkObject(Entity entity, boolean isPlayer){
        int index = 999;

        for(int i = 0; i < gp.item.length; i++){
            if(gp.item[i] != null){

                //Get entity solid area position
                  entity.solidArea.x = entity.worldx + entity.solidArea.x;
                  entity.solidArea.y = entity.worldy + entity.solidArea.y;


                //Get the object solid area position
                gp.item[i].solidArea.x = gp.item[i].worldX + gp.item[i].solidArea.x;
                gp.item[i].solidArea.y = gp.item[i].worldY + gp.item[i].solidArea.y;

                switch (entity.direction){
                    case "up":
                        entity.solidArea.y -= entity.speed;
                        if(entity.solidArea.intersects(gp.item[i].solidArea)){ //checks if two tiles are touching or not
                         if(gp.item[i].collision){
                             entity.collisionOn = true;
                         }

                         if(isPlayer){
                             index = i;
                         }

                        }
                        break;
                    case "down":
                        entity.solidArea.y += entity.speed;
                        if(entity.solidArea.intersects(gp.item[i].solidArea)){ //checks if two tiles are touching or not
                            if(gp.item[i].collision){
                                entity.collisionOn = true;
                            }

                            if(isPlayer){
                                index = i;
                            }

                        }
                        break;
                    case "left":
                        entity.solidArea.x -= entity.speed;
                        if(entity.solidArea.intersects(gp.item[i].solidArea)){ //checks if two tiles are touching or not
                            if(gp.item[i].collision){
                                entity.collisionOn = true;
                            }

                            if(isPlayer){
                                index = i;
                            }

                        }
                        break;
                    case"right":
                        entity.solidArea.x += entity.speed;
                        if(entity.solidArea.intersects(gp.item[i].solidArea)){ //checks if two tiles are touching or not
                            if(gp.item[i].collision){
                                entity.collisionOn = true;
                            }

                            if(isPlayer){
                                index = i;
                            }

                        }
                        break;
                }
                entity.solidArea.x = entity.solidAreaDefaultX;
                entity.solidArea.y = entity.solidAreaDefaultY;
                gp.item[i].solidArea.x = gp.item[i].solidAreaDefaultX;
                gp.item[i].solidArea.y = gp.item[i].solidAreaDefaultY;
            }

        }
        return index;
    }
}
