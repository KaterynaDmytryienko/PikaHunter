package Controller;

import Model.Entity;
import Model.Key;
import View.GamePannel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class KeyHandler implements KeyListener {

    public boolean pressedUp, pressedDown, pressedLeft, pressedRight, pressedEnter, pressedSpace;

    private GamePannel gp;

    public KeyHandler(GamePannel gamePannel) {
        this.gp = gamePannel;
    }

    /**
     * Invokes when a key has been typed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Sets variables pressedUp, pressedDown, pressedRight and pressedLeft to "true"
     * depending on a key that has been pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        //TITLE STATE
        if (gp.gameState == gp.titleState) {
            if (code == KeyEvent.VK_W) {
                gp.userInterface.setCommandNum(gp.userInterface.getCommandNum() - 1);
                if (gp.userInterface.getCommandNum() < 0) {
                    gp.userInterface.setCommandNum(2);
                }
            }

            if (code == KeyEvent.VK_S) {
                gp.userInterface.setCommandNum(gp.userInterface.getCommandNum() + 1);
                if (gp.userInterface.getCommandNum() > 2) {
                    gp.userInterface.setCommandNum(0);
                }
            }

            if(code == KeyEvent.VK_ENTER){ // start new game
                if(gp.userInterface.getCommandNum() == 0){
                    gp.gameState = gp.playState;
                }
                if(gp.userInterface.getCommandNum() == 1){
                    //LOAD GAME
                }

                if(gp.userInterface.getCommandNum() == 2){
                    System.exit(0);
                }
            }
        }
        if (code == KeyEvent.VK_UP) {//press up
            pressedUp = true;
        }

        if (code == KeyEvent.VK_DOWN) {//press down
            pressedDown = true;

        }

        if (code == KeyEvent.VK_LEFT) {//press left
            pressedLeft = true;

        }

        if (code == KeyEvent.VK_RIGHT) {//press right
            pressedRight = true;

        }

        if (code == KeyEvent.VK_ENTER) {//press right
            pressedEnter = true;

        }

        if (code == KeyEvent.VK_SPACE) { // press space for attack
            pressedSpace = true;
        }


    }

    /** Sets variables pressedUp, pressedDown, pressedRight and pressedLeft to "false"
     * depending on a key that has been released.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased (KeyEvent e){
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP) {//press up
            pressedUp = false;
        }

        if (code == KeyEvent.VK_DOWN) {//press down
            pressedDown = false;
        }

        if (code == KeyEvent.VK_LEFT) {//press left
            pressedLeft = false;
        }

        if (code == KeyEvent.VK_RIGHT) {//press right
            pressedRight = false;
        }

        if (code == KeyEvent.VK_SPACE) {//press right
            pressedSpace = false;
        }
    }
}
