package View;

import Controller.KeyHandler;
import Model.Player;

import javax.swing.*;
import java.awt.*;

public class GamePannel extends JPanel implements Runnable {
    final int ORIGINAL_SIZE = 16; // size for characters and items
    final int SCALE = 3;
    public final int PLAYER_SIZE = ORIGINAL_SIZE * SCALE; // 48*48 size of a character
    final int MAX_SCREEN_COL = 16;
    final int MAX_SCREEN_ROW = 12;
    final int SCREEN_WIDTH = PLAYER_SIZE * MAX_SCREEN_COL; //768px
    final int SCREEN_HEIGHT = PLAYER_SIZE * MAX_SCREEN_ROW;//576px

    //setting FPS for the game
    int FPS = 60;

    Thread gameThread;

    Player player;

    public GamePannel(KeyHandler keyHandler) {
        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyHandler);
        this.setFocusable(true);
        player = new Player(this, keyHandler);
    }


    /**
     * starts main game loop
     */
    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    /**
     * creates main game loop
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
     * invokes method update() on a Player instance
     */
    public void update() {
        player.update();
    }


    /**
     * draws Player instance on a screen
     *
     * @param g the <code>Graphics</code> object to protect
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        player.draw(g2);
        g2.dispose();
    }
}

