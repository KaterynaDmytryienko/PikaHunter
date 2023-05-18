package Controller;

import Model.Tile;
import View.GamePannel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileManager {
    GamePannel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePannel gp){
        this.gp = gp;
        tile = new Tile[10]; // maximum 10 kinds of tiles
        mapTileNum = new int[gp.maxworldcol][gp.maxworldrow];
        getTileImage();
        loadMap("/maps/worldmap.txt");
    }

    /**
     * Gets a specific tile image and assigns it to a specific index of a tile array.
     */
    public void getTileImage(){
        try{
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[2].setCollision(true);

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tile[3].setCollision(true);

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].setCollision(true);

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass00.png"));




        }catch (IOException e){
            e.printStackTrace();
        }
    }


    /**
     * Method loads an entire map
     * @param path
     */
    public void loadMap(String path){
        try{
            InputStream is = getClass().getResourceAsStream(path);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));

            int col = 0;
            int row = 0;
            while (col<gp.maxworldcol && row< gp.maxworldrow){
                String line = bufferedReader.readLine();

                while (col< gp.maxworldcol){
                    String numbers[] = line.split(" "); // getting numbers from the map and put them into an array
                    int num = Integer.parseInt(numbers[col]);

                    mapTileNum[col][row] = num;
                    col++;
                }

                if(col == gp.maxworldcol){
                    col = 0;
                    row++;
                }
            }
            bufferedReader.close();
        }
        catch (Exception e){

        }
    }

    /**
     * Method draws entire world map as player moves on a screen.
     * @param g2
     */
    public void draw(Graphics2D g2){
        int worldcol = 0;
        int worldrow = 0;

        while (worldcol<gp.maxworldcol && worldrow<gp.maxworldrow){

            int tileNum = mapTileNum[worldcol][worldrow];

            int worldX = worldcol * gp.playerSize;
            int worldY = worldrow * gp.playerSize;

            int screenX = worldX - gp.player.worldx + gp.player.screenx;
            int screenY = worldY - gp.player.worldy + gp.player.screeny;

            if(worldX + gp.playerSize > gp.player.worldx - gp.player.screenx && worldX - gp.playerSize < gp.player.worldx + gp.player.screenx &&  // creating a boundary for drawing a tile
            worldY + gp.playerSize > gp.player.worldy - gp.player.screeny && worldY - gp.playerSize <  gp.player.worldy + gp.player.screeny){
                g2.drawImage(tile[tileNum].image, screenX, screenY, gp.playerSize, gp.playerSize, null);
            }

            worldcol++;

            if(worldcol == gp.maxworldcol){
                worldcol = 0; // resetting column and increase row by 1
                worldrow++;

            }

        }

    }
}
