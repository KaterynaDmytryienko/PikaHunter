package Controller;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean pressedUp, pressedDown, pressedLeft, pressedRight;

    /**
     * invokes when a key has been typed
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**sets variables pressedUp, pressedDown, pressedRight and pressedLeft to "true"
     * depending on a key that has been pressed
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



    }

    /**sets variables pressedUp, pressedDown, pressedRight and pressedLeft to "false"
     * depending on a key that has been released
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
