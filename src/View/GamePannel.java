package View;

import Controller.*;
import Model.*;
import data.SaveLoad;
import org.w3c.dom.ls.LSOutput;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Logger;

public class GamePannel extends JPanel implements Runnable {
    static final int  ORIGINALSIZE = 16; // size for characters and items
    static final int SCALE = 3;
    public int playerSize = ORIGINALSIZE * SCALE; // 48*48 size of a character
    static final int MAXSCREENCOL = 16;
    static final int MAXSCREENROW = 12;
    public final int screenWidth = playerSize * MAXSCREENCOL; //768px
    public final int screenHeight = playerSize * MAXSCREENROW;//576px

    //WORLD MAP SETTINGS
    public static final int MAXWORLDCOL = 50;
    public static final int MAXWORLDROW = 50;

    //setting FPS for the game
    static final int FPS = 60;
    private final KeyHandler keyHandler = new KeyHandler(this);
    private final TileManager tileManager = new TileManager(this);
    private final UserInterface userInterface = new UserInterface(this);
    private final EventHandler eventHandler = new EventHandler(this);
    Thread gameThread;
    private int characterState = 4;
    private int gameOverState = 6;
    private int dialogueState = 3;

    private CollisionController collisionController = new CollisionController(this);

    private AssetSetter assetSetter = new AssetSetter(this);
    private SaveLoad saveLoad = new SaveLoad(this);
    public Player player;
    public LinkedList<Item> items = new LinkedList<>();//preparing 10 slots for objects(displaying up to 10 objects at the same time)

    //CREATING MONSTER ARRAY
    private Entity monster[] = new Entity[20];
    private InteractiveTile iTile[] = new InteractiveTile[30];

    public Entity[] getMonster() {
        return monster;
    }

    public InteractiveTile[] getiTile() {
        return iTile;
    }

    //GAME STATE
    public int gameState;
    private int playState = 1;
    private int pauseState = 2;

    private int titleState = 0;
    private static final Logger logger = Logger.getLogger(GamePannel.class.getName());

    public int getCharacterState() {
        return characterState;
    }

    public int getGameOverState() {
        return gameOverState;
    }

    public int getDialogueState() {
        return dialogueState;
    }

    public int getPlayState() {
        return playState;
    }

    public int getPauseState() {
        return pauseState;
    }

    public int getTitleState() {
        return titleState;
    }

    public TileManager getTileManager() {
        return tileManager;
    }


    public UserInterface getUserInterface() {
        return userInterface;
    }

    public EventHandler getEventHandler() {
        return eventHandler;
    }

    public CollisionController getCollisionController() {
        return collisionController;
    }

    public AssetSetter getAssetSetter() {
        return assetSetter;
    }

    public SaveLoad getSaveLoad() {
        return saveLoad;
    }

    public GamePannel() throws IOException {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(this.keyHandler);
        this.setFocusable(true);
        player = new Player(this, this.keyHandler);
    }

    public void setMonster(Entity[] monster) {
        this.monster = monster;
    }

    /**
     * Method sets up game layout and sets game state to title state in the beginning of a game.
     * @throws IOException
     */
    public void setupGame() throws IOException {
        assetSetter.setObject();
        assetSetter.setMonster();
        assetSetter.setInteractiveTiles();
        gameState = titleState;
        logger.info("Game is set up.");

    }

    /**
     * Method sets action when player wants to retry the game when he died.
     * @throws IOException
     */
    public void retry() throws IOException {
        player.setDefaultPositions();
        player.restoreLife();

        assetSetter.setMonster();
    }

    /**
     * Method sets all default values and layout of a game.
     * @throws IOException
     */
    public void restart() throws IOException {
        player.setDefault();
        player.setDefaultPositions();
        player.setItems();
        assetSetter.setObject();
        assetSetter.setMonster();
        assetSetter.setInteractiveTiles();
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
            try {
                update();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
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
    public void update() throws IOException {
        if(gameState == playState){

            //PLAYER UPDATE
            player.update();

            //MONSTER UPDATE
            for(int i = 0; i < monster.length; i++){
                if(monster[i]!=null){
                    monster[i].update();
                }
            }
            for(int i = 0; i < iTile.length; i++){
                if(iTile[i]!=null){
                    iTile[i].update();
                }
            }
        }
    }



    /**
     * Draws entities and objects on a screen depending on a current game state.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        //TITLE SCREEN
        if(gameState == titleState){
            logger.info("Game state is Title state now.");
            userInterface.drawTitleScreen(g2);
        }

        else {
            //TILE
            tileManager.draw(g2);

            //INTERACTIVE TILE
            for(int i = 0; i < iTile.length; i++){
                if(iTile[i]!= null){
                    iTile[i].draw(g2, this);
                }
            }

            //OBJECT
            for (Item item : items) {
                item.draw(g2, this);
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

