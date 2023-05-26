package Controller;

import Model.Entity;
import Model.Key;
import View.GamePannel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.logging.Logger;


public class KeyHandler implements KeyListener {
    private static final Logger logger = Logger.getLogger(KeyHandler.class.getName());

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
     * Checks current state of a game and calls the appropriate method depending on the state.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();


        //TITLE STATE
        if (gp.gameState == gp.getTitleState()) {
            try {
                titleState(code);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            } catch (ClassNotFoundException ex) {
                throw new RuntimeException(ex);
            }
        }
        //PLAY STATE
        else if (gp.gameState == gp.getPlayState()) {
            playState(code);
        }
        //CHARACTER STATE
        else if(gp.gameState == gp.getCharacterState()){
            try {
                characterState(code);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //GAME OVER STATE
        else if(gp.gameState == gp.getGameOverState()){
            try {
                gameOverState(code);
                logger.info("Game state is Game over state.");
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }

        //PAUSE STATE
        else if(gp.gameState == gp.getPauseState()){
            pauseState(code);
            logger.info("Game state is Pause state.");

        }

        //DIALOG STATE
        else if(gp.gameState == gp.getDialogueState()){
            dialogueState(code);
        }
    }

    /**
     * Defines pause state actions.
     * @param code
     */
    public void pauseState(int code){
        if(code == KeyEvent.VK_P){
            gp.gameState = gp.getPlayState();
            logger.info("Back to Play state.");
        }
    }

    /**
     * Defines dialogue state actions.
     * @param code
     */
    public void dialogueState(int code){
        if(code == KeyEvent.VK_Y){
            gp.gameState = gp.getPlayState();
            gp.player.worldy += 20;
        }
    }

    /**
     * Defines title state actions.
     * @param code
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void titleState(int code) throws IOException, ClassNotFoundException {
        if (code == KeyEvent.VK_W) {
            gp.getUserInterface().setCommandNum(gp.getUserInterface().getCommandNum() - 1);
            if (gp.getUserInterface().getCommandNum() < 0) {
                gp.getUserInterface().setCommandNum(2);
            }
        }

        if (code == KeyEvent.VK_S) {
            gp.getUserInterface().setCommandNum(gp.getUserInterface().getCommandNum() + 1);
            if (gp.getUserInterface().getCommandNum() > 2) {
                gp.getUserInterface().setCommandNum(0);
            }
        }

        if (code == KeyEvent.VK_ENTER) { // start new game
            if (gp.getUserInterface().getCommandNum() == 0) {
                gp.gameState = gp.getPlayState();
            }
            if (gp.getUserInterface().getCommandNum() == 1) {
                gp.getSaveLoad().load();
                gp.gameState = gp.getPlayState();
            }

            if (gp.getUserInterface().getCommandNum() == 2) {
                System.exit(0);
            }
        }
    }

    /**
     * Defines play state actions.
     * @param code
     */
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
            gp.gameState = gp.getCharacterState();
        }

        if (code == KeyEvent.VK_ENTER) {//press right
            setPressedEnter(true);
        }

        if (code == KeyEvent.VK_SPACE) { // press space for attack
            setPressedSpace(true);
        }

        if(code == KeyEvent.VK_P){
            gp.gameState = gp.getPauseState();
        }


    }

    /**
     * Defines actions for the inventory.
     * @param code
     * @throws IOException
     */
    public void characterState(int code) throws IOException {
        if(code == KeyEvent.VK_C){
            gp.gameState = gp.getPlayState();
        }
        if(code == KeyEvent.VK_W){
            if(gp.getUserInterface().slotRow != 0) {
                gp.getUserInterface().slotRow--;
            }
        }

        if(code == KeyEvent.VK_A){
            if(gp.getUserInterface().slotCol != 0) {
                gp.getUserInterface().slotCol--;
            }
        }
        if(code == KeyEvent.VK_S){
            if(gp.getUserInterface().slotRow != 3) {
                gp.getUserInterface().slotRow++;
            }
        }
        if(code == KeyEvent.VK_D){
            if(gp.getUserInterface().slotCol!=4) {
                gp.getUserInterface().slotCol++;
            }
        }
        if(code == KeyEvent.VK_ENTER){
            gp.player.selectItem();
            logger.info("Item is selected.");
        }
    }

    /**
     * Defines action for game over state.
     * @param code
     * @throws IOException
     */
    public void gameOverState(int code) throws IOException {
        if(code == KeyEvent.VK_W){
            gp.getUserInterface().setCommandNum(gp.getUserInterface().getCommandNum()-1);
            if(gp.getUserInterface().commandNum < 0){
                gp.getUserInterface().setCommandNum(1);
            }
        }

        if(code == KeyEvent.VK_S){
            gp.getUserInterface().setCommandNum(gp.getUserInterface().getCommandNum()+1);
            if(gp.getUserInterface().commandNum > 1){
                gp.getUserInterface().setCommandNum(0);
            }
        }
        if(code == KeyEvent.VK_ENTER){
            if(gp.getUserInterface().getCommandNum() == 0){
                gp.gameState = gp.getPlayState();
                gp.retry();
                logger.info("Retrying game.");
            }
            else if(gp.getUserInterface().getCommandNum() == 1){
                gp.gameState = gp.getTitleState();
                gp.restart();
                logger.info("Restarted game.");

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
