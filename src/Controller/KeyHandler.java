package Controller;

import Model.Entity;
import Model.Key;
import View.GamePannel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;


public class KeyHandler implements KeyListener {

    private boolean pressedUp, pressedDown, pressedLeft, pressedRight, pressedEnter, pressedSpace;

    public boolean isPressedUp() {
        return pressedUp;
    }

    public void setPressedUp(boolean pressedUp) {
        this.pressedUp = pressedUp;
    }

    public boolean isPressedDown() {
        return pressedDown;
    }

    public void setPressedDown(boolean pressedDown) {
        this.pressedDown = pressedDown;
    }

    public boolean isPressedLeft() {
        return pressedLeft;
    }

    public void setPressedLeft(boolean pressedLeft) {
        this.pressedLeft = pressedLeft;
    }

    public boolean isPressedRight() {
        return pressedRight;
    }

    public void setPressedRight(boolean pressedRight) {
        this.pressedRight = pressedRight;
    }

    public boolean isPressedEnter() {
        return pressedEnter;
    }

    public void setPressedEnter(boolean pressedEnter) {
        this.pressedEnter = pressedEnter;
    }

    public boolean isPressedSpace() {
        return pressedSpace;
    }

    public void setPressedSpace(boolean pressedSpace) {
        this.pressedSpace = pressedSpace;
    }

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
            try {
                titleState(code);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        //PLAY STATE
        else if (gp.gameState == gp.playState) {
            playState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.characterState){
            try {
                characterState(code);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //GAME OVER STATE
        else if(gp.gameState == gp.gameOverState){
            try {
                gameOverState(code);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //PAUSE STATE
        else if(gp.gameState == gp.pauseState){
            pauseState(code);
        }

        //DIALOG STATE
        else if(gp.gameState == gp.dialogueState){
            dialogueState(code);
        }
    }

    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.playState;
        }
    }

    public void dialogueState(int code){
        if(code == KeyEvent.VK_Y){
            gp.gameState = gp.playState;
            gp.player.worldy += 20;
        }
    }

    public void titleState(int code) throws IOException, ClassNotFoundException {
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

        if (code == KeyEvent.VK_ENTER) { // start new game
            if (gp.userInterface.getCommandNum() == 0) {
                gp.gameState = gp.playState;
            }
            if (gp.userInterface.getCommandNum() == 1) {
                gp.saveLoad.load();
                gp.gameState = gp.playState;
            }

            if (gp.userInterface.getCommandNum() == 2) {
                System.exit(0);
            }
        }
    }

    public void playState(int code){
        if (code == KeyEvent.VK_UP) {//press up
            setPressedUp(true);
        }

        if (code == KeyEvent.VK_DOWN) {//press down
            setPressedDown(true);

        }

        if (code == KeyEvent.VK_LEFT) {//press left
            setPressedLeft(true);

        }

        if (code == KeyEvent.VK_RIGHT) {//press right
            setPressedRight(true);

        }
        //setting key for a character state
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.characterState;
        }

        if (code == KeyEvent.VK_ENTER) {//press right
            setPressedEnter(true);
        }

        if (code == KeyEvent.VK_SPACE) { // press space for attack
            setPressedSpace(true);
        }

        if(code == KeyEvent.VK_P){
            gp.gameState = gp.pauseState;
        }


    }

    public void characterState(int code) throws IOException {
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.playState;
        }
        if(code == KeyEvent.VK_W){
            if(gp.userInterface.slotRow != 0) {
                gp.userInterface.slotRow--;
            }
        }

        if(code == KeyEvent.VK_A){
            if(gp.userInterface.slotCol != 0) {
                gp.userInterface.slotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.userInterface.slotRow != 3) {
                gp.userInterface.slotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.userInterface.slotCol!=4) {
                gp.userInterface.slotCol++;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
        }
    }

    public void gameOverState(int code) throws IOException {
        if(code == KeyEvent.VK_W){
            gp.userInterface.setCommandNum(gp.userInterface.getCommandNum()-1);
            if(gp.userInterface.commandNum < 0){
                gp.userInterface.setCommandNum(1);
            }
        }

        if(code == KeyEvent.VK_S){
            gp.userInterface.setCommandNum(gp.userInterface.getCommandNum()+1);
            if(gp.userInterface.commandNum > 1){
                gp.userInterface.setCommandNum(0);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.userInterface.getCommandNum() == 0){
                gp.gameState = gp.playState;
                gp.retry();
            }
            else if(gp.userInterface.getCommandNum() == 1){
                gp.gameState = gp.titleState;
                gp.restart();
            }
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
            setPressedUp(false);
        }

        if (code == KeyEvent.VK_DOWN) {//press down
            setPressedDown(false);
        }

        if (code == KeyEvent.VK_LEFT) {//press left
            setPressedLeft(false);
        }

        if (code == KeyEvent.VK_RIGHT) {//press right
            setPressedRight(false);
        }

        if (code == KeyEvent.VK_SPACE) {//press right
           setPressedSpace(false);
        }
    }
}
