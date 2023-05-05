package View;

import Controller.*;
import Model.Enemy;
import Model.Entity;
import Model.Item;
import Model.Player;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class GamePannel extends JPanel implements Runnable {
    final int originalSize = 16; // size for characters and items
    final int scale = 3;
    public final int playerSize = originalSize * scale; // 48*48 size of a character
    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;
    public final int screenWidth = playerSize * maxScreenCol; //768px
    public final int screenHeight = playerSize * maxScreenRow;//576px

    //WORLD MAP SETTINGS
     public final int maxworldcol = 50;
    public final int maxworldrow = 50;
    public final int worldwidth = playerSize * maxworldcol;
    public final int worldheight = playerSize * maxworldrow;

    //setting FPS for the game
    public int FPS = 60;
    private final KeyHandler keyHandler = new KeyHandler(this);
    public TileManager tileManager = new TileManager(this);
    public UserInterface userInterface = new UserInterface(this);
    public EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;

    public CollisionController collisionController = new CollisionController(this);

    public AssetSetter assetSetter = new AssetSetter(this);
    public Player player;
    public Item item[] = new Item[10]; //preparing 10 slots for objects(displaying up to 10 objects at the same time)

    //CREATING MONSTER ARRAY
    public Entity monster[] = new Entity[20];




    //GAME STATE
    public int gameState;
    public final int playState = 1;
//    public final int pauseState = 2;

    public final int titleState = 0;




    public GamePannel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        player = new Player(this, this.keyHandler);
    }

    public void setupGame() throws IOException {
        assetSetter.setObject();
        assetSetter.setMonster();
        gameState = titleState;
    }


    /**
     * Starts main game loop.
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * Creates main game loop.
     */
    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS; // 1 second / FPS
        double nextDrawTime = System.nanoTime() + drawInterval;


        while (gameThread != null) {
            update();
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0; //no time left, so Thread.sleep will sleep for 0 seconds
                }
                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * Invokes method update() on a Player instance.
     */
    public void update() {
        if(gameState == playState){

            //PLAYER UPDATE
            player.update();

            //MONSTER UPDATE
            for(int i = 0; i < monster.length; i++){
                if(monster[i]!=null){
                    monster[i].update();
                }
            }
        }
    }



    /**
     * Draws Player instance on a screen.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if(gameState == titleState){
            userInterface.drawTitleScreen(g2);
        }

        else {
            //TILE
            tileManager.draw(g2);

            //OBJECT
            for(int i = 0; i < item.length; i++){
                if(item[i] != null){
                    item[i].draw(g2, this);
                }
            }

            //MONSTER
            for(int i = 0; i < monster.length; i++) {
                if (monster[i] != null) {
                    monster[i].draw(g2, this);
                }
            }

            //PLAYER
            player.draw(g2);
            userInterface.draw(g2);
            g2.dispose(); 
        }

    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }
}

