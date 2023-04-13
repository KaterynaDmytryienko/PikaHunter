package View;

import Controller.KeyHandler;
import Model.Player;
import Controller.TileManager;

import javax.swing.*;
import java.awt.*;

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

    TileManager tileManager = new TileManager(this);
    Thread gameThread;

    public Player player;

    public GamePannel(KeyHandler keyHandler) {
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player = new Player(this, keyHandler);
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
        player.update();
    }


    /**
     * Draws Player instance on a screen.
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tileManager.draw(g2);
        player.draw(g2);
        g2.dispose();
    }
}

