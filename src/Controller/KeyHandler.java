package Controller;

import Model.Key;
import View.GamePannel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean pressedUp, pressedDown, pressedLeft, pressedRight;
    /**
     * Invokes when a key has been typed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /** Sets variables pressedUp, pressedDown, pressedRight and pressedLeft to "true"
     * depending on a key that has been pressed.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP){//press up
            pressedUp = true;
        }

        if (code == KeyEvent.VK_DOWN){//press down
            pressedDown = true;

        }

        if (code == KeyEvent.VK_LEFT){//press left
            pressedLeft = true;

        }

        if (code == KeyEvent.VK_RIGHT){//press right
            pressedRight = true;

        }


        if (code == KeyEvent.VK_P){//press right
           if(GamePannel.gameState == GamePannel.playState){
               GamePannel.gameState = GamePannel.pauseState;
           } else if (GamePannel.gameState == GamePannel.pauseState) {
               GamePannel.gameState = GamePannel.playState;
           }

        }



    }

    /** Sets variables pressedUp, pressedDown, pressedRight and pressedLeft to "false"
     * depending on a key that has been released.
     *
     * @param e the event to be processed
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();

        if (code == KeyEvent.VK_UP){//press up
            pressedUp = false;
        }

        if (code == KeyEvent.VK_DOWN){//press down
            pressedDown = false;
        }

        if (code == KeyEvent.VK_LEFT){//press left
            pressedLeft = false;
        }

        if (code == KeyEvent.VK_RIGHT){//press right
            pressedRight = false;
        }
    }
}
